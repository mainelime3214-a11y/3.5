package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    // Тестовые данные (book1 и book2 содержат "ук" в названии)
    private Book book1 = new Book(1, "Азбука", 500, "Иванов");
    private Book book2 = new Book(2, "Букварь", 600, "Петров");
    private Smartphone phone1 = new Smartphone(3, "Samsung S21", 50000, "Samsung");
    private Product product1 = new Product(4, "Мышка", 1000);
    private Product product2 = new Product(5, "Клавиатура", 3000);

    @BeforeEach
    void setUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(phone1);
        manager.add(product1);
        manager.add(product2);
    }

    @Test
    void shouldAddAndFindAll() {
        Product[] expected = {book1, book2, phone1, product1, product2};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByExistingName() {
        Product[] expected = {product2};
        Product[] actual = manager.searchBy("Клавиатура");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByPartialName() {
        Product[] expected = {phone1};
        Product[] actual = manager.searchBy("Sam");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByMultipleMatches() {
        // Исправлено: ожидаем 2 совпадения (book1: "Азбука", book2: "Букварь" -> оба содержат "ук")
        Product[] expected = {book1, book2};
        Product[] actual = manager.searchBy("ук");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyArrayIfNoMatch() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("НетТакого");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldMatchTheSearchCriteria() {
        assertTrue(manager.matches(book1, "Азбука"));
        // Проверяем, что matches не срабатывает, если ищем не по имени
        assertFalse(manager.matches(book1, "Иванов"));
    }

    @Test
    void shouldSearchWhenNoProductsInRepository() {
        ProductRepository emptyRepository = new ProductRepository();
        ProductManager emptyManager = new ProductManager(emptyRepository);

        Product[] expected = {};
        Product[] actual = emptyManager.searchBy("ЛюбойТекст");
        assertArrayEquals(expected, actual);
    }
}