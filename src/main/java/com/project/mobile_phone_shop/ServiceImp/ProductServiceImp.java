package com.project.mobile_phone_shop.ServiceImp;

import com.project.mobile_phone_shop.Dto.ProductDto;
import com.project.mobile_phone_shop.Dto.ProductImportDto;
import com.project.mobile_phone_shop.Entity.Product;
import com.project.mobile_phone_shop.Entity.ProductImportHistory;
import com.project.mobile_phone_shop.Entity.Sale;
import com.project.mobile_phone_shop.Exception.NotFoundException;
import com.project.mobile_phone_shop.Repository.ImportHistoryRepository;
import com.project.mobile_phone_shop.Response.ApiResponse;
import com.project.mobile_phone_shop.Service.ProductService;
import com.project.mobile_phone_shop.Mapper.Mapper;
import com.project.mobile_phone_shop.Repository.ProductRepository;
import com.project.mobile_phone_shop.Validate.Validate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;
    private final ImportHistoryRepository importHistoryRepository;
    private final Validate validate;
    private final Mapper mapper;

    @Override
    public Product createProduct(ProductDto productDto) {
        Product product = validate.setProduct(productDto);
        return productRepository.save(product);
    }

    @Override
    public Product getByProductId(Long id) {
        return validate.ValidateProductNotFound(id);
    }

    @Override
    @Transactional
    public void importProduct(ProductImportDto productImportDto) {
        //save
        Product product = getByProductId(productImportDto.getProductId());
        Product importProduct = validate.ValidateImportProduct(product,productImportDto.getImportUnit());
        productRepository.save(importProduct);

        //save import history
        ProductImportHistory importHistory = mapper.mapToProductImportHistory(productImportDto, product);
        importHistoryRepository.save(importHistory);
    }

    @Override
    public void setSalePrice(Long productId, BigDecimal price) {
        Product product = getByProductId(productId);
        product.setSalePrice(price);
        productRepository.save(product);
    }

    @Override
    public Map<Integer,String> uploadProduct(MultipartFile file) {
        Map<Integer,String> map = new HashMap<>();
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheets = workbook.getSheet("Products");
            Iterator<Row> rowIterator = sheets.iterator();
            rowIterator.next();

            while(rowIterator.hasNext()) {
                Integer rowNumber = 0;
                try {
                    Row row = rowIterator.next();
                    Integer cellIndex = 0;

                    Cell cellNo = row.getCell(cellIndex++);
                    rowNumber = (int) cellNo.getNumericCellValue();

                    Cell cellModelId = row.getCell(cellIndex++);
                    Long modelId = (long) cellModelId.getNumericCellValue();

                    Cell cellColorId = row.getCell(cellIndex++);
                    Long colorId = (long) cellColorId.getNumericCellValue();

                    Cell cellImportPrice = row.getCell(cellIndex++);
                    double importPrice = cellImportPrice.getNumericCellValue();

                    Cell cellImportUnit = row.getCell(cellIndex++);
                    Integer importUnit = (int) cellImportUnit.getNumericCellValue();

                    Cell cellImportDate = row.getCell(cellIndex++);
                    LocalDateTime importDate = cellImportDate.getLocalDateTimeCellValue();

                    Product product = getByModelIdAndColorId(modelId, colorId);

                    Product importProduct = validate.ValidateImportProduct(product,importUnit);
                    productRepository.save(importProduct);

                    ProductImportHistory importHistory = new ProductImportHistory();
                    importHistory.setImportDate(importDate);
                    importHistory.setImportUnit(importUnit);
                    importHistory.setProduct(product);
                    importHistory.setPricePerUnit(BigDecimal.valueOf(importPrice));
                    importHistoryRepository.save(importHistory);
                } catch (Exception e) {
                    map.put(rowNumber, e.getMessage());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    @Override
    public Product getByModelIdAndColorId(Long modelId, Long colorId) {
        return productRepository
                .findByModelIdAndColorId(modelId,colorId)
                .orElseThrow(NotFoundException::new);
    }
}
