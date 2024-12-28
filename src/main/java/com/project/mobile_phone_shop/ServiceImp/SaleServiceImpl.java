package com.project.mobile_phone_shop.ServiceImp;

import com.project.mobile_phone_shop.Dto.ProductSoldDto;
import com.project.mobile_phone_shop.Dto.SaleDto;
import com.project.mobile_phone_shop.Entity.Product;
import com.project.mobile_phone_shop.Entity.Sale;
import com.project.mobile_phone_shop.Entity.SaleDetail;
import com.project.mobile_phone_shop.Repository.ProductRepository;
import com.project.mobile_phone_shop.Repository.SaleDetailRepository;
import com.project.mobile_phone_shop.Repository.SaleRepository;
import com.project.mobile_phone_shop.Response.ApiResponse;
import com.project.mobile_phone_shop.Service.ProductService;
import com.project.mobile_phone_shop.Service.SaleDetailService;
import com.project.mobile_phone_shop.Service.SaleService;
import com.project.mobile_phone_shop.Validate.Validate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
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

    private void updateStock(List<ProductSoldDto> products, Map<Long, Product> mapProduct) {
        products.forEach(p -> {
            Product product = mapProduct.get(p.getProductId());
            product.setAvailableUnit(product.getAvailableUnit() - p.getNumberOfUnit());
            productRepository.save(product);
        });
    }


}
