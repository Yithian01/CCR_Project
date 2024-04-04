package view;

import controller.FavoriteController;
import controller.IceBoxController;
import controller.RecipeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame{
	 private FavoriteController fCon = new FavoriteController();
	 private IceBoxController iCon = new IceBoxController();
	 private RecipeController rCon = new RecipeController();
	 
	 
	 
	 public MainView() {
	    	super("쿨쿨 냉장고");
	    	setLayout(null);
	    	ImageIcon cool1=new ImageIcon("C:\\Edu\\JavaWork\\ccrPro02\\image\\main1.png");
	    	ImageIcon cool2=new ImageIcon("C:\\Edu\\JavaWork\\ccrPro02\\image\\main2.png");
	    	JLabel backgroundLabel1 = new JLabel(cool1);
	    	JLabel backgroundLabel2 = new JLabel(cool2);
            backgroundLabel1.setBounds(10, 10, 100, 100);
            backgroundLabel2.setBounds(320, 390, 200,200);
            add(backgroundLabel1);
            add(backgroundLabel2);
            
	        
	        Font titleFont = new Font("맑은 고딕", Font.BOLD, 25);
	        JLabel titleLabel = new JLabel("쿨쿨 냉장고");
	        titleLabel.setFont(titleFont);
	        titleLabel.setBounds(174, 20, 200, 30);
	        add(titleLabel);

	        Font buttonFont = new Font("맑은 고딕", Font.BOLD, 15);
	        JButton fridgeButton = new JButton("냉장고 보기");
	        JButton recipeButton = new JButton("레시피");
	        JButton favButton = new JButton("즐겨찾기");

	        fridgeButton.setFont(buttonFont);
	        recipeButton.setFont(buttonFont);
	        favButton.setFont(buttonFont);
	        
	        fridgeButton.setBounds(144, 100, 200, 60);
	        recipeButton.setBounds(144, 190, 200, 60);
	        favButton.setBounds(144, 280, 200, 60);

	        
	        add(fridgeButton);
	        add(recipeButton); 
	        add(favButton);

	        setSize(500, 600);
	        setVisible(true);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);

	        fridgeButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	SwingUtilities.invokeLater(() -> {
	            		// 냉장고 보기
	            		iCon.iceBoxSelectAll();
	            
	            	});
	            }
	        });

	        recipeButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	SwingUtilities.invokeLater(() -> {
	            		// 레시피 보기
	            		rCon.selectByMakeRecipe(getWarningString(), getName());
	                
	            	});
	            }
	        });

	        favButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	SwingUtilities.invokeLater(() -> {
	            		// 즐겨찾기 보기
	            		fCon.selectFavorite();
	            		
	                });
	            }
	        });

	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new MainView();
	            }
	        });
	    }
	

}
