package com.wenkchan.androidricheditor;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 输入内容
     */
    @Bind(R.id.et_edit_diary_content)
    EditWalkView mEtEditDiaryContent;
    /**
     * 黑体
     */
    @Bind(R.id.btn_rich_bar_bold)
    ImageButton mBtnRichBarBold;
    /**
     * 删除线
     */
    @Bind(R.id.btn_rich_bar_strike_through)
    ImageButton mBtnRichBarStrikeThrough;
    /**
     * 无序列表
     */
    @Bind(R.id.btn_rich_bar_bullet)
    ImageButton mBtnRichBarBullet;
    /**
     * 有序列表
     */
    @Bind(R.id.btn_rich_bar_list_number)
    ImageButton mBtnRichBarListNumber;
    /**
     * 字体颜色
     */
    @Bind(R.id.btn_rich_bar_text_color)
    ImageButton mBtnRichBarTextColor;
    /**
     * 字体大小
     */
    @Bind(R.id.btn_rich_bar_text_size)
    ImageButton mBtnRichBarTextSize;
    @Bind(R.id.lin_rich_bar_text_size_divider)
    View mLinRichBarTextSizeDivider;
    /**
     * 改变字体大小栏
     */
    @Bind(R.id.lin_rich_bar_text_size)
    LinearLayout mLinRichBarTextSize;
    /**
     * 默认字体
     */
    @Bind(R.id.tv_default_text_size)
    TextView mTvDefaultTextSize;
    /**
     * 增大字体
     */
    @Bind(R.id.btn_rich_bar_text_size_increase)
    ImageButton mBtnRichBarTextSizeIncrease;
    /**
     * 减小字体
     */
    @Bind(R.id.btn_rich_bar_text_size_decrease)
    ImageButton mBtnRichBarTextSizeDecrease;
    private Context mContext=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        init();
    }

    protected void init() {
        ButterKnife.bind(this);
        mBtnRichBarBold.setOnClickListener(this);
        mBtnRichBarBullet.setOnClickListener(this);
        mBtnRichBarListNumber.setOnClickListener(this);
        mBtnRichBarStrikeThrough.setOnClickListener(this);
        mBtnRichBarTextColor.setOnClickListener(this);
        mBtnRichBarTextSize.setOnClickListener(this);
        mBtnRichBarTextSizeDecrease.setOnClickListener(this);
        mBtnRichBarTextSizeIncrease.setOnClickListener(this);
        mTvDefaultTextSize.setOnClickListener(this);
        mEtEditDiaryContent.setEditorFontSize(30);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rich_bar_bold://黑体
                mEtEditDiaryContent.setBold();
                break;
            case R.id.btn_rich_bar_strike_through://删除线
                mEtEditDiaryContent.setStrikeThrough();
                break;
            case R.id.btn_rich_bar_bullet://无序列表
                mEtEditDiaryContent.setBullets();
                break;
            case R.id.btn_rich_bar_list_number://有序列表
                mEtEditDiaryContent.setNumbers();
                break;
            case R.id.btn_rich_bar_text_color://字体颜色
                showChooseColorDialog();
                break;
            case R.id.btn_rich_bar_text_size://字体大小
                if (View.VISIBLE == mLinRichBarTextSize.getVisibility()) {
                    mLinRichBarTextSize.setVisibility(View.GONE);
                    mLinRichBarTextSizeDivider.setVisibility(View.GONE);
                } else {
                    mLinRichBarTextSize.setVisibility(View.VISIBLE);
                    mLinRichBarTextSizeDivider.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_rich_bar_text_size_increase://增大字体
                mEtEditDiaryContent.setEditorFontSizeBigger();
                break;
            case R.id.btn_rich_bar_text_size_decrease://减小字体
                mEtEditDiaryContent.setEditorFontSmaller();
                break;
            case R.id.tv_default_text_size://默认字体大小
                mEtEditDiaryContent.setEditorFontDefault();
                break;

        }

    }


    private AlertDialog.Builder mDialogBuilder;

    private Dialog mDialog;

    /**
     * 选择颜色对话框
     */
    private void showChooseColorDialog() {
        mDialogBuilder = new AlertDialog.Builder(mContext);
        mDialogBuilder.setCancelable(true);
        GridView selectPanel = (GridView) LayoutInflater.from(mContext).inflate(R.layout.dialog_color_select_panel, null, false);
        final List<ColorPanel> colorPanel = new ArrayList<>();
        colorPanel.add(new ColorPanel(ColorPanel.BLACK));
        colorPanel.add(new ColorPanel(ColorPanel.RED));
        colorPanel.add(new ColorPanel(ColorPanel.ORANGE));
        colorPanel.add(new ColorPanel(ColorPanel.GREEN));
        colorPanel.add(new ColorPanel(ColorPanel.BLUE));
        colorPanel.add(new ColorPanel(ColorPanel.PURPLE));
        selectPanel.setAdapter(new CommonAdapter<ColorPanel>(mContext, colorPanel, R.layout.item_color_select_panel) {
            @Override
            public void convert(Holder holder, ColorPanel color) {
                LinearLayout panel = holder.getView(R.id.card_color_panel);
                panel.setBackgroundColor(color.getColor());
            }
        });
        selectPanel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mEtEditDiaryContent.setTextColor(colorPanel.get(position).getColor());
                mDialog.dismiss();
            }
        });

        mDialogBuilder.setView(selectPanel);
        mDialog = mDialogBuilder.show();
    }


}
