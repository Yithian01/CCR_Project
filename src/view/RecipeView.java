
package view;

import controller.RecipeController;
import dto.RecipeDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class RecipeView extends JFrame {
	private RecipeController rCon = new RecipeController();
	 private Font f1 = new Font("맑은 고딕", Font.BOLD, 15);
	 private JTable jt1 ;
	 private Vector data = new Vector();
	 private Vector col = new Vector();
	 private String iLocation ;
	 private String iName;
	 JMenuBar menuBar ;
	 JMenu recipeMenu ;
	 JMenuItem recipeMenuItem ; 
	
	 
    public  RecipeView(List<RecipeDTO> list)  {
        super("레시피");
        
        /**
         *사단 메뉴바 설계 
         */
        menuBar= new JMenuBar();
        recipeMenu= new JMenu("보기");
        recipeMenuItem = new JMenuItem("모든 레시피");
        		
        recipeMenuItem.addActionListener(e -> {
        	rCon.selectRecipe();
        });
        
        
        
        setJMenuBar(menuBar);
        recipeMenu.add(recipeMenuItem);
        menuBar.add(recipeMenu);
     
        
       
      
        
        /**
         * 가져온 데이터 조작
         */
        iLocation = "";
        int tmp = list.size();
        for (int i= 0; i <tmp; i++ ) {
        	iLocation = list.get(i).getRecipeName();
        	iName = "C:\\Edu\\JavaWork\\ccrPro02\\image\\" + iLocation + ".png";
        	ImageIcon imageIcon = new ImageIcon(iName);
        	Vector row = new Vector();
        	
        	row.add(imageIcon);
        	row.add(list.get(i).getRecipeName());
        	row.add(list.get(i).getMainStuff());
        	
        	data.add(row);
        }
        
        col.addElement("사진");
     	col.addElement("이름");
     	col.addElement("주재료");
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        DefaultTableModel model1 = new DefaultTableModel(data, col)
        {
            @Override
            public Class<?> getColumnClass(int column) {
                 // 사진경로가 담긴 icon 객체를 String이 아닌 이미지로 알려줌
                 return getValueAt(0, column).getClass();
            }
       };
       
       
       jt1 = new JTable(model1);
       jt1.setFont(f1);
       jt1.setRowHeight(100);
       jt1.getTableHeader().setReorderingAllowed(false); // 열 순서 변경 금지
       jt1.getTableHeader().setResizingAllowed(false); // 열크기 변경 금지
       jt1.getColumnModel().getColumn(0).setPreferredWidth(100);
       jt1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);  
       jt1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); 
       

        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
        JScrollPane js = new JScrollPane(jt1, v, h);
        add(js, BorderLayout.CENTER);
        setSize(500, 600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    };
}

