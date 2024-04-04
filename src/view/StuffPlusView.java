package view;

import controller.IceBoxController;
import dto.IceBoxDTO;
import dto.StuffDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class StuffPlusView {
    private JFrame frame;
    private JPanel mainPanel;
    private JTable typeTable;
    private JTable nameTable;
    private JSpinner quantitySpinner;
    private JButton addButton;
    private JButton cancelButton;
    private ButtonGroup bg1 ;
    Vector< StuffDTO>[] stuffData;
    
    private List<StuffDTO> list;
    private IceBoxController iCon =  new IceBoxController();
    
    private JRadioButton rb1,rb2;
    Font f1 = new Font("맑은 고딕", Font.BOLD, 13);

    
    public StuffPlusView(  List<StuffDTO> list) {
    	this.list = list;
        frame = new JFrame("재료 추가");
        frame.setSize(1100, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(null); // 레이아웃 매니저를 사용하지 않음

        
        //---------------< 가져온 데이터 정리 >-----------------
       int listSize = list.size();
       stuffData = new Vector[15];
      for(int i =0; i< 15; i++) {
    	  stuffData[i] = new Vector<StuffDTO>();
      }
       for (int i=0; i <listSize; i++) {
    	   int stType =  list.get(i).getStType() - 1;
    	   stuffData[ stType ].add(  list.get(i)  );
       }
        
       
       //이름 데이터
       Object[][] nameData = new Object[15][100];
       for (int i= 0; i< 15; i++) {
    	   int stLen = stuffData[i].size();
    	   for(int j =0; j < stLen; j++ ) {
    		   String stName = stuffData[i].get(j).getStuffName();
    		   nameData[i][j] =  stName;
    	   }
       } 
       
       
        // 종류 테이블
        String[] typeColumnNames = {"종류"};
        Object[][] typeData = {{"과일"}, {"채소"}, {"고기"},{"수산물"},{"유제품"},{"요리"},{"음료"},{"주류"},{"조미료"},{"향신료"},{"빵류"},{"디저트"},{"견과류"},{"곡류"},{"기타"}};
        DefaultTableModel typeTableModel = new DefaultTableModel(typeData, typeColumnNames);
        typeTable = new JTable(typeTableModel);
        typeTable.setFont(f1);

        typeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        typeTable.getSelectionModel().addListSelectionListener(e -> updateNameTable(nameData));
        JScrollPane typeScrollPane = new JScrollPane(typeTable);
        typeScrollPane.setBounds(15, 15, 300, 420); // setBounds로 크기와 위치 지정
        mainPanel.add(typeScrollPane);


        // 이름 데이터
       String[] nameColumnNames = {"이름"};
       DefaultTableModel nameTableModel = new DefaultTableModel(nameData, nameColumnNames);
        nameTable = new JTable(nameTableModel);
        nameTable.setFont(f1);
        JScrollPane nameScrollPane = new JScrollPane(nameTable);
        nameScrollPane.setBounds(315, 15, 300, 420);
        mainPanel.add(nameScrollPane);

        //수량 라벨
        JLabel quantityLabel = new JLabel("수량");
        quantityLabel.setBounds(645, 50, 50, 30); // setBounds로 크기와 위치 지정
        mainPanel.add(quantityLabel);

        // 수량 스피너
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
        quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(680, 50, 60, 30);
        mainPanel.add(quantitySpinner);

        
        
        // 추가 버튼
        addButton = new JButton("추가");
        addButton.setFont(f1);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSelectedItem();
            }
        });

        addButton.setBounds(630, 390, 60, 30);
        mainPanel.add(addButton);

        // 취소 버튼
        cancelButton = new JButton("취소");
        cancelButton.setFont(f1);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        cancelButton.setBounds(700, 390, 60, 30);
        mainPanel.add(cancelButton);

        // 냉장, 냉동 라디오 버튼
        rb1 = new JRadioButton("냉장", true);
        rb2 = new JRadioButton("냉동", true);
        rb1.setBounds(640, 210, 60, 30);
        rb2.setBounds(700, 210, 60, 30);
        rb1.setFont(f1);
        rb2.setFont(f1);
        rb1.setActionCommand("냉장");
        rb2.setActionCommand("냉동");
        bg1 = new ButtonGroup();
        bg1.add(rb1);
        bg1.add(rb2);
        mainPanel.add(rb1);
        mainPanel.add(rb2);


        frame.add(mainPanel);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    /**
     * @param nameData
     * 카테고리를 누를 때마다 그에 해당하는 재료를 업데이트
     */
    private void updateNameTable( Object[][] nameData) {
        int selectedRow = typeTable.getSelectedRow();
        if (selectedRow != -1) {
         
            int caLen = nameData[selectedRow].length;
            String[] names = new String[ caLen ];
            for (int i =0 ; i<caLen; i++ ) {
            	String tmp =(String) nameData[selectedRow][i];
            	names[i] = tmp;
            }
            
           DefaultTableModel nameTableModel = (DefaultTableModel) nameTable.getModel();
            nameTableModel.setRowCount(0);
            for (String name : names) {
                nameTableModel.addRow(new Object[]{name});
            }
        }
    }

    /**
     * 추가 버튼으로 현재 값 가져가서 ICEBOX에 넣기
     */
    private void addSelectedItem() {
        int stuffNameSelect = nameTable.getSelectedRow();
        String stuffName = nameTable.getValueAt(stuffNameSelect, 0).toString();
        String selectedValue = bg1.getSelection().getActionCommand();
        int isFrozen = 0;
        if (selectedValue.equalsIgnoreCase("냉동")) {
        	isFrozen = 1;
        }
        
        int quantity = (int) quantitySpinner.getValue();
        
        
        if ( quantity > 0  && stuffName.length() > 0  ) {
        	IceBoxDTO iceBox = new IceBoxDTO( 0, quantity, 0, stuffName, isFrozen);
        	iCon.insertIceBox(iceBox);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "제대로 선택해 주세요!.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}