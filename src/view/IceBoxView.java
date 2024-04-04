
package view;

import controller.IceBoxController;
import controller.StuffController;
import dto.IceBoxDTO;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class IceBoxView extends JFrame implements MouseListener {
    private StuffController sCon = new StuffController();
    private IceBoxController iCon = new IceBoxController();
    
    private Font f1 = new Font("맑은 고딕", Font.BOLD, 15);
    
    
    private int curTableCode =0;
    private JScrollPane jsp = null;
    private JTable jt1, jt2 ;
    private JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP);
    private Vector data = new Vector();
    private Vector fData = new Vector();
    private Vector col = new Vector();

    private List<IceBoxDTO> list;
    private List<IceBoxDTO> iceZero =new ArrayList<>();;
    private List<IceBoxDTO> iceOne = new ArrayList<>();;
    private int iLocation ;
    
    public IceBoxView(  List<IceBoxDTO> list ) {
    	super("MY 냉장고 보기");
    	this.list = list;
    	
        setLayout(null);
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        
        /**
         *  가져온 데이터 가공
         */
        iLocation= 1;
        String iName=String.format("C:\\Edu\\JavaWork\\ccrPro02\\image\\%d.jpg", iLocation);
        //ImageIcon imageIcon = new ImageIcon(iName);
        
        int tmp = list.size();
        for (int i= 0; i <tmp; i++ ) {
        	int isFrozen = list.get(i).getIsFrozen();
        	iLocation =  list.get(i).getStType();
        	iName=String.format("C:\\Edu\\JavaWork\\ccrPro02\\image\\%d.jpg", iLocation);
        	ImageIcon imageIcon = new ImageIcon(iName);
        	Vector row = new Vector();
        	
        	row.add(imageIcon);
        	row.add(list.get(i).getStuffName());
        	row.add(list.get(i).getQuantity());
        	row.add(list.get(i).getLeftDays());
        	row.add(list.get(i).getBoxNum());
        	row.add(list.get(i).getBoxNum());
        	
        	if (isFrozen == 0) {
        		this.iceZero.add(  list.get(i) );
        		data.add(row);
        	}else {
        		this.iceOne.add(  list.get(i) );
        		fData.add(row);
        	}
        }
       
        col.addElement("사진");
     	col.addElement("이름");
     	col.addElement("수량");
     	col.addElement("남은일");
     	col.addElement("삭제");
        
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
        jt1.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer(jt1));
        jt1.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(jt1));
        
        jt1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);  
        jt1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); 
        jt1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); 

        
        JScrollPane jsp1 = new JScrollPane(jt1);
        jp1.setLayout(new BorderLayout());
        jp1.add(jsp1);
        jp1.add(jt1.getTableHeader(), BorderLayout.NORTH);
        jt1.addMouseListener(this);

        DefaultTableModel model2 = new DefaultTableModel(fData,col)
        {
            @Override
            public Class<?> getColumnClass(int column) {
                 // 어떤 행이든 간에 입력된 column의 class를  반환하도록 한 것
                 return getValueAt(0, column).getClass();
            }
       };
        jt2 = new JTable(model2);
        jt2.setFont(f1);
        jt2.setRowHeight(100);
        
        jt2.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer(jt2));
        jt2.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(jt2));
        
        jt2.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);  
        jt2.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); 
        jt2.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        JScrollPane jsp2 = new JScrollPane(jt2);
        jp2.setLayout(new BorderLayout());
        jp2.add(jsp2);
        jp2.add(jt2.getTableHeader(), BorderLayout.NORTH);
        jt2.addMouseListener(this);

        jtp.addTab("냉장", jp1);
        jtp.addTab("냉동", jp2);

        RoundButton1 roundButton = new RoundButton1("+");
        roundButton.setFont(new Font("Arial", Font.BOLD, 27));
        jtp.setTabComponentAt(0, roundButton);

        roundButton.setBounds(390, 460, 50, 50);
        add(roundButton);

        roundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sCon.selectStuff( 1 );
            }
        });

        jtp.setBounds(10, 10, 470, 540);
        getContentPane().add(jtp);

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(575,190,500,600);
        
        
     // 색상을 변경할 열의 인덱스 (0부터 시작)
        final int dayCol = 3;

        DefaultTableCellRenderer dayToColor = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
              
                
                
                // 특정 열에 대한 색상을 변경하려면 열의 인덱스를 확인
                if (column == dayCol) {
                    // 여기서 원하는 색상을 지정
                	 int leftDay = (int) table.getModel().getValueAt(row, column);
                	 if ( leftDay < 0) {
                		 component.setBackground(Color.BLACK);
                	 }
                	 else if (leftDay < 4) {
                         component.setBackground(Color.RED);
                     } else if (leftDay < 7) {
                         component.setBackground(Color.ORANGE);
                     }else {
                    	 component.setBackground(Color.GREEN);
                     }
                }
                
            
                setHorizontalAlignment(JLabel.CENTER);

                return component;
            }
        };

        // 모델에 렌더러 설정
        jt1.getColumnModel().getColumn(dayCol).setCellRenderer(dayToColor);
        jt2.getColumnModel().getColumn(dayCol).setCellRenderer(dayToColor);
     
    
        //-------------------------<jtp변경 시 >------------------------
        jtp.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = jtp.getSelectedIndex();

                // Check which tab is currently selected
                if (selectedIndex == 0) {
                    // jt1 is selected
                    curTableCode = 0;
                } else if (selectedIndex == 1) {
                    // jt2 is selected
                	curTableCode = 1;
                }
            }
        });
        
    }
    
    public int getCurTableCode() {
    	return this.curTableCode;
    }
    
    //------------------------<JTable에 X버튼추가>----------------------
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
    
    //----------------------------<삭제 이벤트>------------------------------
  class ButtonEditor extends DefaultCellEditor {
    	
        private static final long serialVersionUID = 1L;
        protected JButton button;


        public ButtonEditor(JTable table) {
            super(new JTextField());
            button = new JButton("Delete");
          
            button.addActionListener(e -> {
                fireEditingStopped();
                // 여기에서 선택된 행을 삭제하는 작업을 수행할 수 있습니다.
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                	
                	// ---- 냉동/ 냉장 분류해서 따로따로 삭제 
                	int curJT = getCurTableCode();
                	
                	
                	if ( curJT == 0) {
                		int boxNum = iceZero.get(selectedRow).getBoxNum();
                		 iCon.deleteIceBox(boxNum);
                         ((DefaultTableModel) table.getModel()).removeRow(selectedRow);
                        
                	}else if(curJT == 1) {
                		int boxNum = iceOne.get(selectedRow).getBoxNum();
                    	iCon.deleteIceBox(boxNum);
                    	((DefaultTableModel) table.getModel()).removeRow(selectedRow);
                	}
               
                   
                
                
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
    
  //-------------------------< 마우스 이벤트 >------------------------
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    
    
}
