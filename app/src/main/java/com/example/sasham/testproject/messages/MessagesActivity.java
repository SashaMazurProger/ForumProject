package com.example.sasham.testproject.messages;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.sasham.testproject.BaseActivity;
import com.example.sasham.testproject.BaseDaggerActivity;
import com.example.sasham.testproject.Constants;
import com.example.sasham.testproject.R;
import com.example.sasham.testproject.model.Theme;
import com.example.sasham.testproject.themes.ThemesListingFragment;
import com.example.sasham.testproject.util.NetworkUtil;
import com.example.sasham.testproject.util.PreferencesHelper;
import com.example.sasham.testproject.util.StringUtil;
import com.example.sasham.testproject.website.WebActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.saket.bettermovementmethod.BetterLinkMovementMethod;

public class MessagesActivity extends BaseDaggerActivity implements BaseActivity.OnConnectionListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.themes_container)
    CoordinatorLayout mainContainer;

    private Snackbar snackbar;
    private boolean isConnected = false;
    private Fragment messagesFragment;
    private Theme theme;

    //Theme
    @BindView(R.id.theme_forum_name)
    TextView forumName;
    @BindView(R.id.theme_topic_text)
    TextView topicName;
    @BindView(R.id.theme_msg_text)
    TextView msgText;
    @BindView(R.id.theme_created)
    TextView createdTime;
    @BindView(R.id.theme_user_name)
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_messages);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setDefaultTitle();

        theme = getIntent().getParcelableExtra(Constants.THEME_MODEL);

        setThemeReview();

        //Показываем фрагменты только если доступна сеть
        if (NetworkUtil.isConnectedNetwork(this)) {

            showMessagesFragment();
        }

        addOnConnectionListener(this);
        startListenInternetState();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.themes_menu, menu);

        MenuItem theme = menu.findItem(R.id.action_black_theme);
        theme.setChecked(PreferencesHelper.getTheme(getApplicationContext()) == Constants.THEME_BLACK);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Изменяем основную тему приложения
        if (item.getItemId() == R.id.action_black_theme) {

            boolean isChecked = !item.isChecked();
            item.setChecked(isChecked);

            if (isChecked) {
                PreferencesHelper.setTheme(getApplicationContext(), Constants.THEME_BLACK);
            } else {
                PreferencesHelper.setTheme(getApplicationContext(), Constants.THEME_WHITE);
            }
            updateTheme();
            recreateActivity();

        }

        return super.onOptionsItemSelected(item);
    }

    private void setThemeReview() {
        Theme currentTheme = theme;

        userName.setText(currentTheme.getUserName());
        forumName.setText(currentTheme.getForumName());
        topicName.setText(currentTheme.getTopicText());

        msgText.setText(StringUtil.stripHtml(currentTheme.getMsgText()));
        msgText.setMovementMethod(BetterLinkMovementMethod.getInstance());
        Linkify.addLinks(msgText, Linkify.WEB_URLS);
        BetterLinkMovementMethod.linkify(Linkify.WEB_URLS, msgText)
                .setOnLinkClickListener(new BetterLinkMovementMethod.OnLinkClickListener() {
                    @Override
                    public boolean onClick(TextView textView, String url) {
                        WebActivity.startActivity(url, MessagesActivity.this);
                        return true;
                    }
                });

        if (StringUtil.isNotNullOrEmpty(currentTheme.getMsgTime())) {
            createdTime.setText(StringUtil.getDateFromMillis(currentTheme.getMsgTime(), Constants.TIME_PATTERN));
        }
    }

    private void setDefaultTitle() {
        setTitle("");
    }

    //Показываем список топиков
    private void showMessagesFragment() {
        messagesFragment = new MessagesFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.themes_listing_container, MessagesFragment.newInstance(theme))
                .commit();
    }


    @Override
    public void internetConnectionChanged(boolean connected) {
        isConnected = connected;

        if (connected) {
            if (snackbar != null) {
                snackbar.dismiss();

                //Если не было сети и не был добавлен фрагмент - добавляем
                if (messagesFragment == null) {
                    showMessagesFragment();
                }
            }

        } else {
            snackbar = Snackbar.make(mainContainer, R.string.connection_failed, Snackbar.LENGTH_INDEFINITE);
            snackbar.show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
