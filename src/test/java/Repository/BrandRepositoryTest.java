package Repository;
import com.project.mobile_phone_shop.Entity.Brand;
import com.project.mobile_phone_shop.MobilePhoneShopApplication;
import com.project.mobile_phone_shop.Repository.BrandRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = MobilePhoneShopApplication.class)
@TestPropertySource("classpath:application-test.yml")
@ActiveProfiles("test")
public class BrandRepositoryTest {
    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void findByName() {
        //given
        Brand brand = new Brand();
        brand.setName("Apple");

        brandRepository.save(brand);

        //when
        Brand name = brandRepository.findByName("Apple");

        //then
        assertEquals(brand.getName(),name.getName());
    }

}
