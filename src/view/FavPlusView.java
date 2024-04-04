package view;

import controller.FavoriteController;
import dto.FavoriteDTO;
import dto.StuffDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class FavPlusView {
	private FavoriteController fCon = new FavoriteController();
    private JFrame frame;
    private JPanel mainPanel;
    private JTable typeTable;
    private JTable nameTable;
    private JButton addButton;
    private JButton cancelButton;
    private List<StuffDTO> list;
    private Vector< StuffDTO>[] stuffData;
    private Font f1 = new Font("맑은 고딕", Font.BOLD, 13);
    
    public FavPlusView( List<StuffDTO> list  ) {
    	this.list = list;
        frame = new JFrame("즐겨찾기 추가");
        frame.setSize(1100, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

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
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        mainPanel.add(typeScrollPane, gbc);

        // 이름 테이블
        String[] nameColumnNames = {"이름"};
        DefaultTableModel nameTableModel = new DefaultTableModel(nameData, nameColumnNames);
        nameTable = new JTable(nameTableModel);
        nameTable.setFont(f1);
        JScrollPane nameScrollPane = new JScrollPane(nameTable);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        mainPanel.add(nameScrollPane, gbc);

        // 추가 버튼
        addButton = new JButton("추가");
        addButton.setFont(f1);
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSelectedItem();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        addButton.setPreferredSize(new Dimension(80, 30));
        mainPanel.add(addButton, gbc);

        // 취소 버튼
        cancelButton = new JButton("취소");
        cancelButton.setFont(f1);
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        cancelButton.setPreferredSize(new Dimension(80, 30));
        mainPanel.add(cancelButton, gbc);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    /**
     * 추가 버튼으로 현재 값 가져가서 Favorite에 넣기
     */
    private void addSelectedItem() {
        int selectedTypeRow = typeTable.getSelectedRow();
        int stuffNameSelect = nameTable.getSelectedRow();
       

        if (selectedTypeRow != -1 && stuffNameSelect != -1 ) {
            String type = typeTable.getValueAt(selectedTypeRow, 0).toString();
            String name = nameTable.getValueAt(stuffNameSelect, 0).toString();
            FavoriteDTO favorite = new FavoriteDTO(0, 0, name, 0 );
            
            fCon.insertFavorite(favorite);
            frame.dispose();
        } else {
        	JOptionPane.showMessageDialog(frame, "제대로 선택해 주세요!.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
}