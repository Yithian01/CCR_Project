package view;

import controller.FavoriteController;
import controller.StuffController;
import dto.FavoriteDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class FavoriteView extends JFrame {
	private FavoriteController  fCon = new FavoriteController();
	private StuffController  sCon = new StuffController();
	
	private JTable jt1;
	private Vector data = new Vector();
	private Vector col = new Vector();
	private int iLocation ;
	private String iName;
	private List<FavoriteDTO> list;
    private Font f1 = new Font("맑은 고딕", Font.BOLD, 15);

    public FavoriteView(  List<FavoriteDTO> list  ) {
            super("즐겨찾기");
            this.list =list;
            iLocation = 1;

            int tmp = list.size();
            for (int i= 0; i <tmp; i++ ) {
            	iLocation = list.get(i).getStType();
            	iName=String.format("C:\\Edu\\JavaWork\\ccrPro02\\image\\%d.jpg", iLocation);
            	ImageIcon imageIcon = new ImageIcon(iName);
            	Vector row = new Vector();
            	
            	row.add(imageIcon);
            	row.add(list.get(i).getStuffName());
            	row.add(list.get(i).getCurrent());
            	
            	data.add(row);
            }
            
            col.addElement("사진");
         	col.addElement("이름");
         	col.addElement("수량");
         	col.addElement("삭제");
         	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
              
            DefaultTableModel model = new DefaultTableModel(data, col)
             {
                  @Override
                  public Class<?> getColumnClass(int column) {
                       // 사진경로가 담긴 icon 객체를 String이 아닌 이미지로 알려줌
                       return getValueAt(0, column).getClass();
                  }
             };
         	
             jt1 = new JTable(model);
             jt1.setFont(f1);
             jt1.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer(jt1));
             jt1.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(jt1));
             jt1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);  
             jt1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); 
            
            RoundButton1 roundButton = new RoundButton1("+");
		    roundButton.setFont(new Font("Arial", Font.BOLD, 27));   
		    roundButton.setBounds(390, 460, 50, 50); // x, y, width, height
		    add(roundButton);

		    roundButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	sCon.selectStuff(2);
		        }
		        
		    });
            
		    
            int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
            int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
            JScrollPane js = new JScrollPane(jt1, v, h);

            js.setBounds(10, 10, 470, 540);
            add(js);
            setSize(500, 600);
            setVisible(true);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        };


    static class ButtonRenderer extends JButton implements TableCellRenderer {

        private static final long serialVersionUID = 1L;

        public ButtonRenderer(JTable table) {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText("X");
            return this;
        }
    }

      class ButtonEditor extends DefaultCellEditor {
    	
        private static final long serialVersionUID = 1L;
        protected JButton button;

        public ButtonEditor(JTable table) {
            super(new JTextField());
            button = new JButton("Delete");
            button.addActionListener(e -> {
                fireEditingStopped();
                // 선택된 행 삭제 작업
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                	
                	int favoritesNum = list.get(selectedRow).getFavoritesNum();
                
                	fCon.deleteIceBox(  favoritesNum);
                	((DefaultTableModel) table.getModel()).removeRow(selectedRow);
                	
                	
                
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return button;
        }
        @Override
        public Object getCellEditorValue() {
            return "";
        }
    }
}




















