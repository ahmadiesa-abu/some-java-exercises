import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

class Product {
	String barcode;
	String name;
	double price;
	int quantity;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Product(String barcode, String name, double price, int quantity) {
		super();
		this.barcode = barcode;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public String getBarcode() {
		return barcode;
	}
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Product [barcode=" + barcode + ", name=" + name + ", price="
				+ price + ", quantity=" + quantity + "]";
	}
}

public class StoreInventory extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Product> products;
	private JTextField newProductBarcode;
	private JTextField newProductName;
	private JTextField newProductPrice;
	private JTextField newProductQuantity;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField searchProductSearchValue;
	private JTextField updateProductBarcodeVal;
	private JTextField updateProductNewVal;
	private JScrollPane searchProductResult;
	private JTextArea searchProductResultTxt;
	
	
	public static String getExceptionTraceString(Exception e){
		StackTraceElement[] trace = e.getStackTrace();
		String stackTraceStr = "\n----------------------\n";
		stackTraceStr += "" + e.getCause() + "\n";
		int index = 0;
		for (StackTraceElement traceElement : trace){
			for (int i=0;i<index;i++){	// this trick is just for formatting
				stackTraceStr += "\t";
			}
			stackTraceStr += traceElement.getClassName() + ":" + traceElement.getMethodName() + ":" + traceElement.getLineNumber() + "\n";
			index++;
		}
		stackTraceStr += "----------------------\n";
		return stackTraceStr;
	}
	
	
	public static ArrayList<Product> findProducts(ArrayList<Product> products, String method, String searchValue){
		ArrayList<Product> results = new ArrayList<Product>();
		boolean add = false;
		for (Product product : products){
			if(method.toLowerCase().equalsIgnoreCase("barcode")){
				String value = product.getBarcode();
				if (value.contains(searchValue)){
					add = true;
				}else{
					add = false;
				}
			}else if(method.toLowerCase().equalsIgnoreCase("productname")){
				String value = product.getName();
				if (value.contains(searchValue)){
					add = true;
				}else{
					add = false;
				}
			}
			if (add){
				results.add(product);
			}
		}
		
		return results;
	}
	
	
	public static boolean updateProducts(ArrayList<Product> products, String barcode, String attribute, String newVal){
		boolean updated = false;
		int index = 0;
		for (Product product : products){
			if (product.getBarcode().equalsIgnoreCase(barcode)){
				if(attribute.toLowerCase().equalsIgnoreCase("product name")){
					product.setName(newVal);
					products.set(index, product);
					updated = true;
				}else if(attribute.toLowerCase().equalsIgnoreCase("price")){
					product.setPrice(Double.parseDouble(newVal));
					products.set(index, product);
					updated = true;
				}else if(attribute.toLowerCase().equalsIgnoreCase("quantity")){
					product.setQuantity(Integer.parseInt(newVal));
					products.set(index, product);
					updated = true;
				}
			}
			index++;
		}
		
		return updated;
	}
	
	
	public StoreInventory(){
		super("Store Inventory");
		
		JLabel newProductLabel = new JLabel("NEW PRODUCT");
		newProductLabel.setForeground(Color.RED);
		
		newProductBarcode = new JTextField();
		newProductBarcode.setColumns(10);
		
		newProductName = new JTextField();
		newProductName.setColumns(10);
		
		newProductPrice = new JTextField();
		newProductPrice.setColumns(10);
		
		newProductQuantity = new JTextField();
		newProductQuantity.setColumns(10);
		
		JLabel newProductBarcodeLabel = new JLabel("Barcode");
		
		JLabel newProductNameLabel = new JLabel("Product Name");
		
		JLabel newProductPriceLabel = new JLabel("Price");
		
		JLabel newProductQuantityLabel = new JLabel("Quantity");
		
		JButton newProductInsert = new JButton("Insert");
		newProductInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchProductResultTxt.setText(""); // clear previous results
				searchProductResultTxt.setText(""); // clear previous results
				try{
					// get values from fields and append to array-list
					String barCode = newProductBarcode.getText() + "";
					String productName = newProductName.getText() + "";
					double productPrice = Double.parseDouble(newProductPrice.getText() + "");
					int productQuantity = Integer.parseInt(newProductQuantity.getText() + "");
					products.add(new Product(barCode, productName, productPrice, productQuantity));
					JOptionPane.showMessageDialog(StoreInventory.this, "Product Inserted");
					// clear fields after insert success
					newProductBarcode.setText("");
					newProductName.setText("");
					newProductPrice.setText("");
					newProductQuantity.setText("");
				}catch(Exception ex){
					JOptionPane.showMessageDialog(StoreInventory.this, getExceptionTraceString(ex));
				}
			}
		});
		
		JButton netProductClear = new JButton("Clear");
		netProductClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// clear all fields
				searchProductResultTxt.setText(""); // clear previous results
				newProductBarcode.setText("");
				newProductName.setText("");
				newProductPrice.setText("");
				newProductQuantity.setText("");

			}
		});
		
		JSeparator newProductSeparator = new JSeparator();
		
		JLabel searchProductLabel = new JLabel("SEARCH PRODUCT BY ");
		searchProductLabel.setForeground(Color.RED);
		
		JRadioButton searchProductChoiceCode = new JRadioButton("Code");
		buttonGroup.add(searchProductChoiceCode);
		
		JRadioButton searchProductChoiceName = new JRadioButton("Product Name");
		buttonGroup.add(searchProductChoiceName);
		
		JButton searchProductFind = new JButton("Find");
		searchProductFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchProductResultTxt.setText(""); // clear previous results
				boolean isSearchByCode = searchProductChoiceCode.isSelected();
				boolean isSearchByName = searchProductChoiceName.isSelected();
				String searchValue = searchProductSearchValue.getText() + "";
				ArrayList<Product> results = new ArrayList<Product>();
				if (isSearchByCode){
					results = findProducts(products, "barcode", searchValue);
				}else if (isSearchByName){
					results = findProducts(products, "productname", searchValue);
				}
				searchProductResultTxt.append("------------------------------------------\n");
				searchProductResultTxt.append("| Bardcode | Product Name | Price | Quantity |\n");
				for (Product product: results){
					searchProductResultTxt.append("| "+ product.getBarcode()+" | "+product.getName()+" | "+product.getPrice()+" | "+product.getQuantity()+" |\n");
				}
				searchProductResultTxt.append("------------------------------------------\n");
			}
		});
		
		searchProductSearchValue = new JTextField();
		searchProductSearchValue.setColumns(10);
		
		searchProductResultTxt = new JTextArea();
		searchProductResult = new JScrollPane(searchProductResultTxt);
		
		JSeparator searchProductSeparator = new JSeparator();
		
		JLabel updateProductLabel = new JLabel("UPDATE PRODUCT");
		updateProductLabel.setForeground(Color.RED);
		
		JLabel updateProductBarcodeLabel = new JLabel("Barcode");
		
		updateProductBarcodeVal = new JTextField();
		updateProductBarcodeVal.setColumns(10);
		
		JComboBox<String> updateProductAttribChoice = new JComboBox<String>();
		updateProductAttribChoice.setModel(new DefaultComboBoxModel<String>(new String[] {"Product Name", "Price", "Quantity"}));
		
		JLabel updateProductNewValLabel = new JLabel("New Value");
		
		updateProductNewVal = new JTextField();
		updateProductNewVal.setColumns(10);
		
		JButton updateProductAction = new JButton("UPDATE");
		updateProductAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the value and which attribute for the provided barcode to update
				searchProductResultTxt.setText(""); // clear previous results
				String barCode = updateProductBarcodeVal.getText() + "";
				String attribute = updateProductAttribChoice.getSelectedItem() + "";
				String newVal = updateProductNewVal.getText() + "";
				try{
					boolean updated = updateProducts(products, barCode, attribute, newVal);
					if (updated){
						JOptionPane.showMessageDialog(StoreInventory.this, "updated successfully");
					}else{
						JOptionPane.showMessageDialog(StoreInventory.this, "update failed");
					}
				}catch(Exception ex){
					JOptionPane.showMessageDialog(StoreInventory.this, getExceptionTraceString(ex));
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(newProductBarcodeLabel))
								.addComponent(newProductBarcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(newProductName, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(50)
									.addComponent(newProductNameLabel)))
							.addGap(23)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(31)
									.addComponent(newProductPriceLabel))
								.addComponent(newProductPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(17)
									.addComponent(newProductQuantityLabel))
								.addComponent(newProductQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(newProductLabel))
					.addContainerGap(107, Short.MAX_VALUE))
				.addComponent(newProductSeparator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(searchProductLabel)
					.addGap(48)
					.addComponent(searchProductChoiceCode)
					.addGap(18)
					.addComponent(searchProductChoiceName)
					.addContainerGap(247, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(updateProductLabel)
					.addContainerGap(531, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(updateProductBarcodeLabel)
					.addGap(18)
					.addComponent(updateProductBarcodeVal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(updateProductAttribChoice, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(updateProductNewValLabel)
					.addGap(18)
					.addComponent(updateProductNewVal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(updateProductAction)
					.addContainerGap(37, Short.MAX_VALUE))
				.addComponent(searchProductSeparator, GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(204)
					.addComponent(newProductInsert)
					.addGap(18)
					.addComponent(netProductClear)
					.addContainerGap(247, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(searchProductResult, GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(searchProductFind)
					.addGap(18)
					.addComponent(searchProductSearchValue, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(181, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(newProductLabel)
							.addGap(3)
							.addComponent(newProductBarcodeLabel)
							.addGap(1)
							.addComponent(newProductBarcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(newProductPriceLabel)
								.addComponent(newProductNameLabel))
							.addGap(1)
							.addComponent(newProductPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(newProductName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(newProductQuantityLabel)
							.addGap(1)
							.addComponent(newProductQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(newProductInsert)
						.addComponent(netProductClear))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(newProductSeparator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(searchProductLabel)
							.addGap(28))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(searchProductChoiceCode)
								.addComponent(searchProductChoiceName))
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchProductFind)
						.addComponent(searchProductSearchValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(searchProductResult, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(searchProductSeparator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(updateProductLabel)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(updateProductNewValLabel))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(updateProductNewVal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(updateProductAction))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(updateProductBarcodeLabel)
							.addComponent(updateProductBarcodeVal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(updateProductAttribChoice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
		);
		getContentPane().setLayout(groupLayout);
		products = new ArrayList<Product>();
		JPanel newProductPanel = new JPanel();
		newProductPanel.setLayout(new BorderLayout());   
		
		
	}
	public static void main (String []argv){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoreInventory frame = new StoreInventory();
					frame.setSize(640, 480);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}   
	
}
