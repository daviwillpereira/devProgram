//package com.devProgram.javaCoreAssessment.repositories;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.devProgram.javaCoreAssessment.entities.Product;
//
//@ExtendWith(MockitoExtension.class)
//class ProductRepositoryTest {
//
//    @Mock
//    private ProductRepository productRepository;
//
//    private Product product1;
//    private Product product2;
//
//    @BeforeEach
//    void init() {
//        product1 = new Product(0L, null, null, null, null, null, null, null);
//    }
//
//    @Test
//    void save() {
//        Mockito.when(productRepository.save(product1)).thenReturn(product1);
//        Mockito.when(productRepository.listAll()).thenReturn(Stream.of(product1).collect(Collectors.toMap(Product::getId, Function.identity())));
//
//        Assertions.assertAll(
//                () -> Assertions.assertSame(productRepository.save(product1), product1),
//                () -> Assertions.assertEquals(productRepository.listAll().size(), 1)
//        );
//    }
//
//    @Test
//    void delete() {
//        Mockito.when(productRepository.save(product1)).thenReturn(product1);
//        Mockito.when(productRepository.save(product2)).thenReturn(product2);
//        Mockito.when(productRepository.delete(product1)).thenReturn(product1);
//        Mockito.when(productRepository.findById(product1.getId())).thenReturn(Optional.empty());
//        Mockito.when(productRepository.findById(product2.getId())).thenReturn(Optional.ofNullable(product2));
//
//        Assertions.assertAll(
//                () -> Assertions.assertSame(productRepository.save(product1), product1),
//                () -> Assertions.assertSame(productRepository.save(product2), product2),
//                () -> Assertions.assertSame(productRepository.delete(product1), product1),
//                () -> Assertions.assertSame(productRepository.findById(product1.getId()), Optional.empty()),
//                () -> Assertions.assertSame(productRepository.findById(product2.getId()).get(), Optional.ofNullable(product2).get())
//        );
//    }
//
//    @Test
//    void findByName() {
//        List<Product> repository = Stream.of(product1).collect(Collectors.toList());
//
//        Mockito.when(productRepository.save(product1)).thenReturn(product1);
//        Mockito.when(productRepository.findByName(product1.getName())).thenReturn(Optional.ofNullable(repository));
//
//        Assertions.assertAll(
//                () -> Assertions.assertSame(productRepository.save(product1), product1),
//                () -> Assertions.assertEquals(productRepository.findByName(product1.getName()).get(), repository)
//        );
//    }
//}