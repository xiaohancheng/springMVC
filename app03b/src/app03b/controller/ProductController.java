package app03b.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app03b.domain.Product;
import app03b.form.ProductForm;
import app03b.service.ProductService;

@Controller
public class ProductController {
	private static final Log logger = LogFactory
			.getLog(ProductController.class);
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/product_input")
	public String inputProduct() {
		logger.info("inputProduct called");
		return "ProductForm";
	}

	@RequestMapping(value = "/product_save")
	public String saveProduct(ProductForm productForm,
			RedirectAttributes redirectAttributes) {
		logger.info("saveProduct called");
		Product product = new Product();
		product.setName(productForm.getName());
		product.setDescription(productForm.getDescription());
		try {
			product.setPrice(Float.parseFloat(productForm.getPrice()));
		} catch (NumberFormatException e) {
		}
		Product savedProduct = productService.add(product);
		redirectAttributes.addFlashAttribute("message",
				"The product was successfully added.");
		redirectAttributes.addFlashAttribute("product",savedProduct);
		return "redirect:/product_view/" + savedProduct.getId();
	}

	@RequestMapping(value = "/product_view/{id}")
	public String viewProduct(@PathVariable Long id/*, Model model*/) {
		/*Product product = productService.get(id);
		model.addAttribute("product", product);*/
		return "ProductDetails";
	}
}