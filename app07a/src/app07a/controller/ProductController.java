package app07a.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import app07a.domain.Product;

@Controller
public class ProductController {

	private static final Log logger = LogFactory
			.getLog(ProductController.class);

	@RequestMapping(value = "/product_input")
	public String inputProduct(Model model) {
		model.addAttribute("product", new Product());
		return "ProductForm";
	}

	@RequestMapping(value = "/product_save")
	public String saveProduct(HttpServletRequest servletRequest,
			@Valid @ModelAttribute Product product, BindingResult bindingResult,
			Model model) {
		 if (bindingResult.hasErrors()) {
	            return "ProductForm";
	        }
		List<MultipartFile> files = product.getImages();
		if (null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {
				String fileName = multipartFile.getOriginalFilename();
				File imageFile = new File(servletRequest.getServletContext()
						.getRealPath("/image"), fileName);
				try {
					multipartFile.transferTo(imageFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// save product here
		model.addAttribute("product", product);
		return "ProductDetails";
	}

}
