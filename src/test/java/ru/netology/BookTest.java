package ru.netology;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testBookGetters() {
        // Вызов конструктора и геттеров для покрытия всех строк
        Book book = new Book(1, "Война и мир", 1000, "Лев Толстой");

        assertEquals(1, book.getId());
        assertEquals("Война и мир", book.getName());
        assertEquals(1000, book.getPrice());
        assertEquals("Лев Толстой", book.getAuthor());
    }
}