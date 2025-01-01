package Service;

import Util.ReportTestHelper;
import com.project.mobile_phone_shop.Dto.ExpenseReportDto;
import com.project.mobile_phone_shop.Entity.Product;
import com.project.mobile_phone_shop.Entity.ProductImportHistory;
import com.project.mobile_phone_shop.Filter.ProductImportHistorySpec;
import com.project.mobile_phone_shop.Repository.ImportHistoryRepository;
import com.project.mobile_phone_shop.Repository.ProductRepository;
import com.project.mobile_phone_shop.Repository.SaleDetailRepository;
import com.project.mobile_phone_shop.Service.ReportService;
import com.project.mobile_phone_shop.ServiceImp.ReportServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    private ReportService reportService;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ImportHistoryRepository importHistoryRepository;
    @Mock
    private SaleDetailRepository saleDetailRepository;

    @BeforeEach
    public void setUp(){
        reportService = new ReportServiceImp(saleDetailRepository,productRepository,importHistoryRepository);
    }

    @Test
    public void MockGetProductReport() {
         //Given
        List<ProductImportHistory> importHistory = ReportTestHelper.getProductImportHistory();
        List<Product> products = ReportTestHelper.getProduct();
        //When
        when(importHistoryRepository.findAll(any(ProductImportHistorySpec.class)))
                .thenReturn(importHistory);
        when(productRepository.findAllById(anyList()))
                .thenReturn(products);

        List<ExpenseReportDto> mockExpenseReport = reportService.getExpenseReport(LocalDate.of(2024,12,29),LocalDate.of(2025,1,1));
        //Then
        Assertions.assertEquals(3,mockExpenseReport.size());
    }
}
