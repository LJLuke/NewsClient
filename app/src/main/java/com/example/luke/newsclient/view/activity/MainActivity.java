package com.example.luke.newsclient.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.luke.newsclient.R;
import com.example.luke.newsclient.adapter.TabFragmentAdapter;
import com.example.luke.newsclient.view.activity.personal.LoginActivity;
import com.example.luke.newsclient.view.activity.personal.PersonActivity;
import com.example.luke.newsclient.view.activity.search.SearchActivity;
import com.example.luke.newsclient.view.fragment.android.AndroidTabFragment;
import com.example.luke.newsclient.view.fragment.ios.IosTabFragment;
import com.example.luke.newsclient.view.fragment.zhihu.ZhiHuTabFragment;
import com.example.luke.newsclient.view.fragment.hot.HotTabFragment;
import com.example.luke.newsclient.view.fragment.inland.InlandTabFragment;
import com.example.luke.newsclient.view.fragment.recommend.RecommendTabFragment;
import com.example.luke.newsclient.view.fragment.picture.PictureTabFragment;
import com.githang.statusbar.StatusBarCompat;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private LinearLayout searchLayout;
    private CircleImageView personAvatar;

    private List<String> mTitleList = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        KeyWordComputer kwc = new KeyWordComputer(1);
//        String title = "维基解密否认斯诺登接受委内瑞拉庇护";
//        String content = "有俄罗斯国会议员，9号在社交网站推特表示，美国中情局前雇员斯诺登，已经接受委内瑞拉的庇护，不过推文在发布几分钟后随即删除。俄罗斯当局拒绝发表评论，而一直协助斯诺登的维基解密否认他将投靠委内瑞拉。　　俄罗斯国会国际事务委员会主席普什科夫，在个人推特率先披露斯诺登已接受委内瑞拉的庇护建议，令外界以为斯诺登的动向终于有新进展。　　不过推文在几分钟内旋即被删除，普什科夫澄清他是看到俄罗斯国营电视台的新闻才这样说，而电视台已经作出否认，称普什科夫是误解了新闻内容。　　委内瑞拉驻莫斯科大使馆、俄罗斯总统府发言人、以及外交部都拒绝发表评论。而维基解密就否认斯诺登已正式接受委内瑞拉的庇护，说会在适当时间公布有关决定。　　斯诺登相信目前还在莫斯科谢列梅捷沃机场，已滞留两个多星期。他早前向约20个国家提交庇护申请，委内瑞拉、尼加拉瓜和玻利维亚，先后表示答应，不过斯诺登还没作出决定。　　而另一场外交风波，玻利维亚总统莫拉莱斯的专机上星期被欧洲多国以怀疑斯诺登在机上为由拒绝过境事件，涉事国家之一的西班牙突然转口风，外长马加略]号表示愿意就任何误解致歉，但强调当时当局没有关闭领空或不许专机降落。";
//        Collection<Keyword> result = kwc.computeArticleTfidf(title, content);
//        System.out.println(result);

        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#e98f36"));
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        searchLayout = findViewById(R.id.search_layout);
        personAvatar = findViewById(R.id.person_avatar);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTitleList.add("热点");
        mTitleList.add("推荐");
        mTitleList.add("国内");
        mTitleList.add("知乎");
        mTitleList.add("图片");
        mTitleList.add("Android");
        mTitleList.add("Ios");

        mFragmentList.add(new HotTabFragment());
        mFragmentList.add(new RecommendTabFragment());
        mFragmentList.add(new InlandTabFragment());
        mFragmentList.add(new ZhiHuTabFragment());
        mFragmentList.add(new PictureTabFragment());
        mFragmentList.add(new AndroidTabFragment());
        mFragmentList.add(new IosTabFragment());

        viewPager.setAdapter(new TabFragmentAdapter(getSupportFragmentManager(), mTitleList, mFragmentList));
        tabLayout.setupWithViewPager(viewPager);

        searchLayout.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SearchActivity.class));
            overridePendingTransition(0, 0);
        });

        personAvatar.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, PersonActivity.class));
        });
    }
}
