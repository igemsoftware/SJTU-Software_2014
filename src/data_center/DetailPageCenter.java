package data_center;

import java.util.ArrayList;

public class DetailPageCenter
{
	private final static int MAX_HISTORY_NUM = 10;
    public ArrayList<BbkDetail> historyList;

    public DetailPageCenter()
    {
        historyList = new ArrayList<BbkDetail>();
    }

    public BbkDetail inquireDetail(String bbkName)
    {
        BbkDetail detailInfo = DatabaseConnector.getDetailByName(bbkName);
        if (historyList.size() >= MAX_HISTORY_NUM)
            historyList.remove(0);
        historyList.add(detailInfo);
        return detailInfo;
    }

    public BbkDetail InquireHistory(int index)
    {
        if (index < 0 || index > MAX_HISTORY_NUM - 1)
        {   System.out.println("Index out of range... ");
            return null;
        }
        historyList.add(historyList.get(index));
        historyList.remove(index);
        return historyList.get(index);
    }
}
