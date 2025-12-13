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

    public void removeById(int id) {
        // 1. Считаем, сколько элементов останется (не совпадет с ID)
        int count = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                count++;
            }
        }

        // Если количество элементов не изменилось, значит, элемент не найден.
        // Выходим, чтобы избежать ошибки "array lengths differ" в тесте shouldNotChangeIfRemoveByIdNotExisting.
        if (count == products.length) {
            return;
        }

        // 2. Создаем новый массив правильного размера
        Product[] tmp = new Product[count];
        int index = 0;

        // 3. Копируем только нужные элементы
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[index] = product;
                index++;
            }
        }
        products = tmp;
    }
}