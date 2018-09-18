package todday.funny.seoulcatcher.viewmodel.bindingAdapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.widget.TextView;

import todday.funny.seoulcatcher.R;
import todday.funny.seoulcatcher.model.Call;
import todday.funny.seoulcatcher.model.History;
import todday.funny.seoulcatcher.model.User;
import todday.funny.seoulcatcher.util.DateFormat;

public class TextBindingAdapter {
    @BindingAdapter({"setTextColor"})
    public static void setTextColor(TextView view, String color) {
        view.setTextColor(Color.parseColor(color));
    }

    @BindingAdapter({"setLevelText"})
    public static void setLevelText(TextView view, String level) {
        Context context = view.getContext();
        String levelText = context.getString(R.string.level_text, level);
        view.setText(levelText);
    }

    @BindingAdapter({"setDdayText"})
    public static void setDdayText(TextView view, String date) {
        Context context = view.getContext();
        view.setText(DateFormat.getDdayStringFromCalendar(context, date));
    }

    @BindingAdapter({"setHistoryText"})
    public static void setHistoryText(TextView view, History history) {
        Context context = view.getContext();
        String text = "";
        if (history.getUser() != null) {
            text = context.getString(R.string.history_user, history.getUser().getName());
        } else if (history.getCall() != null) {
            text = context.getString(R.string.history_call, history.getCall().getToUserName());
        } else if (history.getSchedule() != null) {
            text = context.getString(R.string.history_scheduel, history.getSchedule().getName());
        }
        view.setText(text);
    }

    @BindingAdapter({"setHistoryDateText"})
    public static void setHistoryDateText(TextView view, long date) {
        view.setText(DateFormat.getStringFromDate(view.getContext(), date));
        /*   Context context = view.getContext();

        view.setText(DateFormat.getDdayStringFromCalendar(context, date));*/
    }

    @BindingAdapter({"setMemberShipLevelText"})
    public static void setMemberShipLevelText(TextView view, User user) {
        Context context = view.getContext();
        if (user != null) {
            String text = context.getString(R.string.member_ship_user_level, user.getName(), user.getLevel());
            view.setText(text);
        }
    }


    @BindingAdapter({"setDiscountText", "setTypeText", "setTypeTextSize"})
    public static void setDiscountText(TextView view, int disCount, String setTypeText, int typeSize) {
        String text = disCount + " " + setTypeText;

        SpannableString spannableString = new SpannableString(text);
        if (typeSize != 0) {
            spannableString.setSpan(new AbsoluteSizeSpan(typeSize, true), text.length() - setTypeText.length(), text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        view.setText(spannableString);
    }


    @BindingAdapter({"setGoldTimeText"})
    public static void setMemberShipLevelText(TextView view, Call call) {
        Context context = view.getContext();
        /*if (user != null) {
            String text = context.getString(R.string.member_ship_user_level, user.getName(), user.getLevel());
            view.setText(text);
        }*/
    }
}
