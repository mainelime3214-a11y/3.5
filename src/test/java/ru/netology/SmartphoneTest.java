package ru.netology;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SmartphoneTest {

    @Test
    void testSmartphoneGetters() {
        // Вызов конструктора и геттеров для покрытия всех строк
        Smartphone phone = new Smartphone(1, "iPhone 13", 75000, "Apple");

        assertEquals(1, phone.getId());
        assertEquals("iPhone 13", phone.getName());
        assertEquals(75000, phone.getPrice());
        assertEquals("Apple", phone.getManufacturer());
    }
}