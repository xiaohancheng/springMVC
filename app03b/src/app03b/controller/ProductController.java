package app03b.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		System.out.println("inputProduct called");
		return "ProductForm";
	}
	//RedirectAttribute可以添加重定向资源，保证重定向时不丢失。
	@RequestMapping(value = "/product_save")
	public String saveProduct(ProductForm productForm,
			RedirectAttributes redirectAttributes) {
		logger.info("saveProduct called");
		System.out.println("saveProduct called");
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
		redirectAttributes.addFlashAttribute("product", savedProduct);
		return "redirect:/product_view/" + savedProduct.getId();
	}

	//@PathVariable可以使用路径变量，@RequestParam可以获取请求参数
	@RequestMapping(value = "/product_view/{id}")
	public String viewProduct(@PathVariable Long id/* , Model model */) {
		/*
		 * Product product = productService.get(id);
		 * model.addAttribute("product", product);
		 */
		return "ProductDetails";
	}

	//modelAttribute用法1：标注一个非请求的处理方法。被@ModelAttribute注解的方法会在每次调用该控制器类的请求处理方法时被调用。
	@ModelAttribute("info")
	public String testModelAttribute() {
		logger.info("modelAttribute called");
		System.out.println("modelAttribute called");
		return "modelAttribute callled";
	}
	@ModelAttribute
	public void testModelAttribute1(Model model){
		logger.info("modelAttribute1 called");
		System.out.println("modelAttribute1 called");
		model.addAttribute("info1","modelAttribute1 called");
	}
	//modelAttribute用法2:标注方法参数，被标注的参数的实例会被添加到Model对象中
}
