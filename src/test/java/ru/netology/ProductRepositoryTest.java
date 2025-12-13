package ru.netology;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Book book1 = new Book(1, "Война и мир", 1000, "Лев Толстой");
    private Smartphone phone1 = new Smartphone(2, "iPhone 13", 75000, "Apple");
    private Product product1 = new Product(3, "Наушники", 5000);

    @Test
    void shouldSaveAndFindAll() {
        repository.save(book1);
        repository.save(phone1);

        Product[] expected = {book1, phone1};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveByIdExisting() {
        repository.save(book1);
        repository.save(phone1);
        repository.save(product1);

        repository.removeById(phone1.getId());

        Product[] expected = {book1, product1};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotChangeIfRemoveByIdNotExisting() {
        repository.save(book1);
        repository.save(phone1);

        repository.removeById(99); // ID, которого нет

        Product[] expected = {book1, phone1}; // Ожидаем 2 элемента
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveLastItem() {
        repository.save(book1);
        repository.removeById(book1.getId());

        Product[] expected = {};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
}

class ProductTest {

    @Test
    void testProductGettersAndSetters() {
        Product product = new Product(10, "Монитор", 15000);

        // Проверка геттеров
        assertEquals(10, product.getId());
        assertEquals("Монитор", product.getName());
        assertEquals(15000, product.getPrice());

        // Проверка сеттеров
        product.setId(20);
        product.setName("Клавиатура");
        product.setPrice(3000);

        assertEquals(20, product.getId());
        assertEquals("Клавиатура", product.getName());
        assertEquals(3000, product.getPrice());
    }
}
