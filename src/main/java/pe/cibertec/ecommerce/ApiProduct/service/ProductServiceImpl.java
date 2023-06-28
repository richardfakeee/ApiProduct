
package pe.cibertec.ecommerce.ApiProduct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.cibertec.ecommerce.ApiProduct.dao.ProductRepository;
import pe.cibertec.ecommerce.ApiProduct.dto.ProductDto;
import pe.cibertec.ecommerce.ApiProduct.entity.Category;
import pe.cibertec.ecommerce.ApiProduct.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ApiRestClient apiRestClient;
    
    @Override
    public Product add(Product product) {
        return productRepository.save(product);
        
    }

    @Override
    public ProductDto findById(Long id) {
        //NUNCA SE HACE ESTO, ESE p ESTA MAL
        Product p = productRepository.findById(id).get();
        //NUNCA SE HACE ESTO, ESE c ESTA MAL
        Category c = apiRestClient.findByIdCategory(p.getIdCategory());
        
        //LEE AL BOB UNCLE
        ProductDto pdto=new ProductDto();
        pdto.setId(p.getId());
        pdto.setProductName(p.getProductName());
        pdto.setUnitPrice(p.getUnitPrice());
        pdto.setCategory(c);
        
        return pdto;
        
    }
    
}
