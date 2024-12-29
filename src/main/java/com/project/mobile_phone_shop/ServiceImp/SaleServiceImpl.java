package com.project.mobile_phone_shop.ServiceImp;

import com.project.mobile_phone_shop.Dto.ProductSoldDto;
import com.project.mobile_phone_shop.Dto.SaleDto;
import com.project.mobile_phone_shop.Entity.Product;
import com.project.mobile_phone_shop.Entity.Sale;
import com.project.mobile_phone_shop.Entity.SaleDetail;
import com.project.mobile_phone_shop.Exception.NotFoundException;
import com.project.mobile_phone_shop.Repository.ProductRepository;
import com.project.mobile_phone_shop.Repository.SaleDetailRepository;
import com.project.mobile_phone_shop.Repository.SaleRepository;
import com.project.mobile_phone_shop.Service.ProductService;
import com.project.mobile_phone_shop.Service.SaleDetailService;
import com.project.mobile_phone_shop.Service.SaleService;
import com.project.mobile_phone_shop.Validate.Validate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final SaleDetailService saleDetailService;
    private final ProductService productService;
    private final Validate validate;

    @Override
    public void sale(SaleDto saleDto) {
        //Fetch productIds from ProductSoldDto
        List<Long> productIds = saleDto.getProducts()
                .stream()
                .map(ProductSoldDto::getProductId)
                .toList();

        //Find all products by id to list
        List<Product> products = productRepository.findAllById(productIds);

        //Map it as key pair value
        Map<Long,Product> mapProducts = products
                .stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        //validate stock
        productIds.forEach(productService::getByProductId);
        validate.validateProductDuringSell(mapProducts,saleDto.getProducts());

        //save sale
        Sale sale = new Sale();
        sale.setSoldDate(saleDto.getSaleDate());
        saleRepository.save(sale);

        //save sale Detail
        saleDetailService.createSaleDetails(saleDto.getProducts(), mapProducts, sale);

        //update stock
        updateStock(saleDto.getProducts(),mapProducts);
    }

    @Override
    public Sale getById(Long saleId) {
        return saleRepository.findById(saleId).orElseThrow(NotFoundException::new);
    }

    @Override
    public void cancelSale(Long saleId) {
        //Update sale status
        Sale sale = getById(saleId);
        sale.setActive(false);
        saleRepository.save(sale);

        //update stock
        List<SaleDetail> saleDetails = saleDetailRepository.findBySaleId(saleId);
        List<Long> productId = saleDetails.stream()
                .map(sd -> sd.getProduct().getId())
                .toList();

        List<Product> products = productRepository.findAllById(productId);

        Map<Long, Product> mapProducts = products.stream()
                .collect(Collectors.toMap(Product::getId,Function.identity()));

        saleDetails.forEach(sd -> {
            Product product = sd.getProduct();
            product.setAvailableUnit(product.getAvailableUnit() + sd.getUnit());
            productRepository.save(product);
        });
    }

    private void updateStock(List<ProductSoldDto> products, Map<Long, Product> mapProduct) {
        products.forEach(p -> {
            Product product = mapProduct.get(p.getProductId());
            product.setAvailableUnit(product.getAvailableUnit() - p.getNumberOfUnit());
            productRepository.save(product);
        });
    }

}
