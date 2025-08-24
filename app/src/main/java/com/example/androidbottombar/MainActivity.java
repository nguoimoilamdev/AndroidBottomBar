package com.example.androidbottombar;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.androidbottombar.ui.BookMarkFragment;
import com.example.androidbottombar.ui.FriendFragment;
import com.example.androidbottombar.ui.HomeFragment;
import com.example.androidbottombar.ui.MenuFragment;
import com.example.androidbottombar.ui.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private LinearLayout btnHome, btnFriend, btnBookmark, btnProfile, btnMenu;
    private ImageView ivHome, ivFriend, ivBookmark, ivProfile, ivMenu;
    private TextView tvHome, tvFriend, tvBookmark, tvProfile, tvMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING
        );

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnHome = findViewById(R.id.bottom_bar_btn_home);
        btnBookmark = findViewById(R.id.bottom_bar_btn_bookmark);
        btnFriend = findViewById(R.id.bottom_bar_btn_friend);
        btnProfile = findViewById(R.id.bottom_bar_btn_profile);
        btnMenu = findViewById(R.id.bottom_bar_btn_menu);

        ivHome = findViewById(R.id.bottom_bar_image_home);
        ivBookmark = findViewById(R.id.bottom_bar_image_bookmark);
        ivFriend = findViewById(R.id.bottom_bar_image_friend);
        ivProfile = findViewById(R.id.bottom_bar_image_profile);
        ivMenu = findViewById(R.id.bottom_bar_image_menu);

        tvHome = findViewById(R.id.bottom_bar_text_home);
        tvBookmark = findViewById(R.id.bottom_bar_text_bookmark);
        tvFriend = findViewById(R.id.bottom_bar_text_friend);
        tvProfile = findViewById(R.id.bottom_bar_text_profile);
        tvMenu = findViewById(R.id.bottom_bar_text_menu);


        choiceFragment(new HomeFragment());
        btnBottomBarSelect(btnHome);

        btnHome.setOnClickListener(v -> {
            AnimationButtonClick(btnHome);
            choiceFragment(new HomeFragment());
            btnBottomBarSelect(btnHome);
        });

        btnFriend.setOnClickListener(v -> {
            AnimationButtonClick(btnFriend);
            choiceFragment(new FriendFragment());
            btnBottomBarSelect(btnFriend);
        });

        btnBookmark.setOnClickListener(v -> {
            AnimationButtonClick(btnBookmark);
            choiceFragment(new BookMarkFragment());
            btnBottomBarSelect(btnBookmark);
        });

        btnProfile.setOnClickListener(v -> {
            AnimationButtonClick(btnProfile);
            choiceFragment(new ProfileFragment());
            btnBottomBarSelect(btnProfile);
        });

        btnMenu.setOnClickListener(v -> {
            AnimationButtonClick(btnMenu);
            choiceFragment(new MenuFragment());
            btnBottomBarSelect(btnMenu);
        });
    }

    private void choiceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_pages, fragment)
                .commit();
    }

    private void btnBottomBarSelect(LinearLayout btnSelect) {
        LinearLayout[] linearLayouts = {btnHome, btnFriend, btnBookmark, btnProfile, btnMenu};
        ImageView[] imageViews = {ivHome, ivFriend, ivBookmark, ivProfile, ivMenu};
        TextView[] textViews = {tvHome, tvFriend, tvBookmark, tvProfile, tvMenu};

        int[] iconOutline = {
                R.drawable.ic_home,
                R.drawable.ic_friend,
                R.drawable.ic_bookmark,
                R.drawable.ic_profile,
                R.drawable.ic_menu
        };

        int[] iconFill = {
                R.drawable.ic_home_fill,
                R.drawable.ic_friend_fill,
                R.drawable.ic_bookmark_fill,
                R.drawable.ic_profile_fill,
                R.drawable.ic_menu_fill
        };

        for (int i = 0; i < linearLayouts.length; i++) {

            if (linearLayouts[i] == btnSelect) {
                imageViews[i].setImageResource(iconFill[i]);
                imageViews[i].setColorFilter(getResources().getColor(R.color.ButtonBarSelect));
                textViews[i].setTextColor(getResources().getColor(R.color.ButtonBarSelect));
                imageViews[i].animate().scaleX(1.2f).scaleY(1.2f).setDuration(150).start();
            } else {

                imageViews[i].setImageResource(iconOutline[i]);
                imageViews[i].setColorFilter(getResources().getColor(R.color.ButtonBarNormal));
                textViews[i].setTextColor(getResources().getColor(R.color.ButtonBarNormal));
                imageViews[i].animate().scaleX(1f).scaleY(1f).setDuration(150).start();

            }
        }

    }

    private void AnimationButtonClick(View view){
        view.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(150)
                .withEndAction(() ->{
                    view.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(150)
                            .start();
                })
                .start();
    }


}