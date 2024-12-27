package Service;

import com.project.mobile_phone_shop.Dto.BrandDto;
import com.project.mobile_phone_shop.Entity.Brand;
import com.project.mobile_phone_shop.Exception.AlreadyExistException;
import com.project.mobile_phone_shop.Mapper.Mapper;
import com.project.mobile_phone_shop.Repository.BrandRepository;
import com.project.mobile_phone_shop.ServiceImp.BrandServiceImp;
import com.project.mobile_phone_shop.Validate.Validate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private Validate validate;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private BrandServiceImp brandService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_add_brand() {
        // Given
        Brand mockBrand = new Brand();
        mockBrand.setName("Apple");
        mockBrand.setId(1);

        BrandDto mockBrandDto = new BrandDto(1, "Apple");
        doNothing().when(validate).ValidateBrandIsExist(mockBrandDto);

        // When
        when(mapper.map(mockBrandDto, Brand.class)).thenReturn(mockBrand);
        when(brandRepository.save(mockBrand)).thenReturn(mockBrand);
        when(mapper.map(mockBrand, BrandDto.class)).thenReturn(mockBrandDto);

        //act
        brandService.addBrand(mockBrandDto);

        // Then
        Assertions.assertEquals(1, mockBrandDto.getId());
        Assertions.assertEquals("Apple", mockBrandDto.getName());
        verify(brandRepository, times(1)).save(mockBrand);
    }

    @Test
    public void Test_add_brand_validate_return_AlreadyExistsException() {
        // Given
        BrandDto brandDto = new BrandDto();
        brandDto.setName("Apple");
        brandDto.setId(1);

        // When
        doThrow(new AlreadyExistException()).when(validate).ValidateBrandIsExist(brandDto);

        // Then
        Assertions.assertThrows(AlreadyExistException.class, () -> brandService.addBrand(brandDto));

        // Verify that the validation method was called
        verify(validate, times(1)).ValidateBrandIsExist(brandDto);
    }

    @Test
    public void Test_getBrandById_Then_Return_BrandDto() {
        //arrange
        Brand mockBrand = new Brand(1,"Apple");

        BrandDto mockBrandDto = new BrandDto(1,"Apple");

        //when
        when(validate.ValidateBrandNotFound(1)).thenReturn(mockBrand);
        when(mapper.map(mockBrand,BrandDto.class)).thenReturn(mockBrandDto);

        //act
        BrandDto dto = brandService.getBrandById(1);

        //then
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(1,dto.getId());
        Assertions.assertEquals("Apple",dto.getName());
        verify(validate, times(1)).ValidateBrandNotFound(1);
    }

    @Test
    public void Test_updateBrand_Then_Return_brandDto() {
        //Given
        Brand mockBrand = new Brand(1,"Samsung");
        BrandDto mockDto = new BrandDto(1, "Samsung");

        when(validate.ValidateBrandNotFound(1)).thenReturn(mockBrand);
        when(mapper.map(mockDto, Brand.class)).thenReturn(mockBrand);
        when(brandRepository.save(any(Brand.class))).thenReturn(mockBrand);
        when(mapper.map(mockBrand, BrandDto.class)).thenReturn(mockDto);

        //act
        BrandDto dto = brandService.updateBrand(1, mockDto);

        //assert
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(1,dto.getId());
        Assertions.assertEquals("Samsung",dto.getName());
        verify(brandRepository, times(1)).save(mockBrand);
    }

    @Test
    public void Test_Delete_Brand_Then_Return_Void() {
        //Arrange
        Brand brand = new Brand(1,"Apple");

        //when
        when(validate.ValidateBrandNotFound(1)).thenReturn(brand);
        //then
        Assertions.assertAll(()-> brandService.deleteBrand(1));
    }
}
