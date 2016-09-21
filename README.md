#ARMs For Android
ARMs for Android is a Android App replanting the website of a CitibankCup work.  Welcome to <b>STAR/FOLLOW/FORK!</b>

>*In this program, we focus on the structure and module design of the application instead of the algo and content of it.*

###Preview
==========
![](https://github.com/AlanDelip/ARMs/blob/master/img/promotion_1_small.png)
![](https://github.com/AlanDelip/ARMs/blob/master/img/promotion_2_small.png)
![](https://github.com/AlanDelip/ARMs/blob/master/img/promotion1.png)
![](https://github.com/AlanDelip/ARMs/blob/master/img/promotion2.png)
###Structures & Design
==========
 - <b>**[MVP (Model+View+Presenter) Structure](https://github.com/googlesamples/android-architecture)**</b>
*A better structure uncoupling between layers, Introduced by Google.*

![](http://upload-images.jianshu.io/upload_images/3072566-d6a0f682aa1e7e1d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
 - <b>[Material Design](http://www.google.com/design/spec/material-design/)</b> 
*For a new vision experience.*

![](http://upload-images.jianshu.io/upload_images/3072566-8edf225ef0bfdbc3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
 - <b>[JakeWharton](https://github.com/JakeWharton)/**[Butterknife](https://github.com/JakeWharton/butterknife)**</b>
*Field and method binding for Android views.*
```
class ExampleActivity extends Activity { 
        @BindView(R.id.title) TextView title; 
        @BindView(R.id.subtitle) TextView subtitle;
        @BindView(R.id.footer) TextView footer; 

        @Override 
        public void onCreate(Bundle savedInstanceState) { 
                super.onCreate(savedInstanceState);
                setContentView(R.layout.simple_activity);
                ButterKnife.bind(this); 
                // TODO Use fields... 
        }

        @Onclick(R.id.btn)
        void click(View view){
                //TODO click event...                
        }
}
```

 - <b>[square](https://github.com/square)/**[Retrofit](https://github.com/square/retrofit)**</b>
*A type-safe HTTP client for Android and Java.*
```
public interface NewsService {
        @GET("news")
        Observable<NewsResponse> getNewsList(@Query("key_word") String keyword);
}
```
 - <b>[ReactiveX](https://github.com/ReactiveX)/**[RxJava](https://github.com/ReactiveX/RxJava)**</b>
*a Java VM implementation of [Reactive Extensions](http://reactivex.io/): a library for composing asynchronous and event-based programs by using observable sequences.*
```
public void getPrediction(Subscriber<PredictResponse> subscriber, String lowerGamma, List<String> optionList) {
        PredictService predictService = retrofit.create(PredictService.class);

        predictService.getPrediction(lowerGamma, optionList)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
```
###Plugins
==========
- [PhilJay](https://github.com/PhilJay)/**[MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)** 
- [aurelhubert](https://github.com/aurelhubert)/**[ahbottomnavigation](https://github.com/aurelhubert/ahbottomnavigation)**
- [ikew0ng](https://github.com/ikew0ng)/**[SwipeBackLayout](https://github.com/ikew0ng/SwipeBackLayout)**
- [DreaminginCodeZH](https://github.com/DreaminginCodeZH)/**[MaterialProgressBar](https://github.com/DreaminginCodeZH/MaterialProgressBar)**

###Supporters
==========
 - Business college, NJU, China
 - Software engineering college, NJU, China

###Developer
==========
 - AlanDelip [@Email Me](mailto:141250164@smail.nju.edu.cn)

###License
==========
Copyright 2016 Alan Delip

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

>http://www.apache.org/licenses/LICENSE-2.0