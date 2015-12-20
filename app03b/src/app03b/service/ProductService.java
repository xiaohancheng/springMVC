package app03b.service;

import app03b.domain.Product;

public interface ProductService {
	Product add(Product product);
	Product get(long id);
}
