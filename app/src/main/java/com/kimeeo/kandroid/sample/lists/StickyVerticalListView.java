package com.kimeeo.kandroid.sample.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kimeeo.kandroid.R;
import com.kimeeo.kandroid.sample.lists.holder.RecyncleItemHolder1;
import com.kimeeo.kandroid.sample.lists.holder.RecyncleItemHolder2;
import com.kimeeo.kandroid.sample.model.SampleModel;
import com.kimeeo.kandroid.sample.projectCore.DefaultProjectDataManager;
import com.kimeeo.library.listDataView.dataManagers.DataManager;
import com.kimeeo.library.listDataView.dataManagers.PageData;
import com.kimeeo.library.listDataView.recyclerView.BaseItemHolder;
import com.kimeeo.library.listDataView.recyclerView.stickyRecyclerHeaders.VerticalList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bhavinpadhiyar on 12/26/15.
 */
public class StickyVerticalListView extends VerticalList implements DefaultProjectDataManager.IDataManagerDelegate
{
    public View getStickyItemView(ViewGroup container)
    {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        return inflater.inflate(R.layout._sample_column_header_cell,container,false);
    }
    public BaseItemHolder getStickyItemHolder(View view)
    {
        return new StickyItemHolder1(view);
    }
    public long getHeaderId(int position)
    {
        if(position<5)
            return 1;
        else if(position<10)
            return 2;
        else if(position<20)
            return 3;
        else if(position<25)
            return 4;
        return 5;
    }

    protected DataManager createDataManager()
    {
        return new DefaultProjectDataManager(getActivity(),this);
    }

    public static class ViewTypes {
        public static final int VIEW_ITEM1 = 5;
        public static final int VIEW_ITEM2 = 10;
    }
    public void onItemClick(Object baseObject)
    {
        super.onItemClick(baseObject);
        Toast.makeText(getActivity(), baseObject.toString(), Toast.LENGTH_SHORT).show();
    }
    //Return View Type here
    @Override
    public int getListItemViewType(int position,Object item)
    {
        if(position<4)
            return ViewTypes.VIEW_ITEM1;
        else
            return ViewTypes.VIEW_ITEM2;
    }
    // get View
    @Override
    public View getItemView(int viewType,LayoutInflater inflater,ViewGroup container)
    {
        return inflater.inflate(R.layout._sample_column_cell,null);
    }
    //END URL
    public String getNextDataURL(PageData pageData)
    {
        return "http://www.googledrive.com/host/0B0GMnwpS0IrNRkI5WFVCZG5EUTQ/data"+pageData.curruntPage+".txt";
    }

    public String getRefreshDataURL(PageData pageData)
    {
        return "http://www.googledrive.com/host/0B0GMnwpS0IrNRkI5WFVCZG5EUTQ/data_m1.txt";
    }

    //Data Parser
    public Class getLoadedDataParsingAwareClass()
    {
        return SampleDataParser.class;
    }





    // get New BaseItemHolder
    @Override
    public BaseItemHolder getItemHolder(int viewType,View view)
    {
        if(viewType== ViewTypes.VIEW_ITEM1)
            return new RecyncleItemHolder1(view);
        else
            return new RecyncleItemHolder2(view);
    }


    public class StickyItemHolder1 extends BaseItemHolder {

        @Bind(R.id.label)TextView label;

        public StickyItemHolder1(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void updateItemView(Object item,View view,int position)
        {
            SampleModel listObject = (SampleModel)item;
            label.setText(position +" -> "+listObject.name.substring(0,1));
        }
    }



}
