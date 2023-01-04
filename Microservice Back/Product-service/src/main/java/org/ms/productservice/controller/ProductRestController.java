package org.ms.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.ms.productservice.model.Category;
import org.ms.productservice.model.Product;
import org.ms.productservice.repository.CategoryRepository;
import org.ms.productservice.repository.ProductRepository;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
//    @PutMapping(path = "/products/{id}")
//    void updateQuantity (@PathVariable(name = "id") Long id, @RequestBody Integer quantity)
//    {
//        Product product= findById(id)
//        product.
//    }
    @PostMapping(path = "/products/new")
    public void saveProductToDB(MultipartFile file, String name, String description, Long price, Integer quantity, Long category) throws IOException {
        Product p = new Product();
        p.setImage(file.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home") + "/microservices/products/" + p.getImage()), file.getBytes());
        p.setDescription(description);
        p.setQuantity(quantity);
        p.setName(name);
        p.setPrice(price);
        p.setCategory(categoryRepository.findById(category).get());
        productRepository.save(p);
//    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//    if(fileName.contains(".."))
//    {
//        System.out.println("not a a valid file");
//    }
//    try {
//        p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
//    } catch (IOException e) {
//        e.printStackTrace();
//    }

    }

    @GetMapping(path = "/products/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable("id") Long id) throws Exception {
        Product p = productRepository.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/microservices/products/" + p.getImage()));
    }
}
