package com.dto;

public class ProductDTO {
	  private Long id;
	    private String name;
	    private CategoryDTO category;
	    
	    
	    
		public ProductDTO() {
			super();
		}

		public ProductDTO(Long id, String name, CategoryDTO category) {
			super();
			this.id = id;
			this.name = name;
			this.category = category;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public CategoryDTO getCategory() {
			return category;
		}

		public void setCategory(CategoryDTO category) {
			this.category = category;
		}
	    
	    
		
    
}
