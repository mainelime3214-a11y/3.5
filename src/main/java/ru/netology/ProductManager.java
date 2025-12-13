package ru.netology;

public class ProductManager {
    private ProductRepository repository;

    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    public void add(Product product) {
        repository.save(product);
    }

    public Product[] searchBy(String text) {
        // 1. Считаем количество совпадений
        int count = 0;
        for (Product product : repository.findAll()) {
            if (matches(product, text)) {
                count++;
            }
        }

        // 2. Создаем массив нужной длины
        Product[] result = new Product[count];
        int index = 0;

        // 3. Заполняем массив совпавшими товарами
        for (Product product : repository.findAll()) {
            if (matches(product, text)) {
                result[index] = product;
                index++;
            }
        }
        return result;
    }

    // Метод определения соответствия товара product запросу search
    public boolean matches(Product product, String search) {
        return product.getName().contains(search);
    }
}