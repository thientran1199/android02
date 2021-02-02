package com.example.doanandroid02.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.doanandroid02.R;
import com.example.doanandroid02.models.Category;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    List<Category> categoryList;
    Context context;

    public CategoryAdapter(List<Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // data load lần đầu thì sẽ map, còn nếu load lần sau thì không cần map lại data
    public class ViewHolder{
        TextView textViewCategory;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_category,null);

            viewHolder.textViewCategory = convertView.findViewById(R.id.textViewCategory);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Category category = (Category) getItem(position);
        viewHolder.textViewCategory.setText(category.getName());

        return convertView;
    }

}
