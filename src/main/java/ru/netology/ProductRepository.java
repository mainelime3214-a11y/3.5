package ru.netology;

public class ProductRepository {
    private Product[] products = new Product[0];

    public void save(Product product) {
        Product[] tmp = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        tmp[tmp.length - 1] = product;
        products = tmp;
    }

    public Product[] findAll() {
        return products;
    }

    /**
     * Ищет товар по его ID.
     *
     * @param id ID искомого товара.
     * @return Объект товара, если найден, иначе null.
     */
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null; // Товар не найден
    }

    public void removeById(int id) {
        // Проверяем, существует ли элемент с таким ID
        if (findById(id) == null) {
            // Если элемент не найден, выбрасываем наше исключение
            throw new NotFoundException("Element with id: " + id + " not found");
        }

        // Если элемент найден, приступаем к удалению
        Product[] tmp = new Product[products.length - 1];
        int index = 0;

        for (Product product : products) {
            // Копируем только те элементы, ID которых не совпадает с искомым
            if (product.getId() != id) {
                tmp[index] = product;
                index++;
            }
        }
        products = tmp;
    }
}