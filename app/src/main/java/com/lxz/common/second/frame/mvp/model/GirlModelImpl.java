package com.lxz.common.second.frame.mvp.model;

import com.lxz.common.R;
import com.lxz.common.second.frame.mvp.bean.Girl;

import java.util.ArrayList;
import java.util.List;


public class GirlModelImpl implements IGirlModel {

	@Override
	public void loadGirl(GirlOnLoadListener listener) {
		//加载数据
		List<Girl> girls = new ArrayList<Girl>();
		girls.add(new Girl("我是一个大美女，哇哈哈哈，你是谁", R.drawable.a));
		girls.add(new Girl("女神现场K歌，屌丝逆袭女神神器。寂寞的人，不寂寞的生活方式，在线美女视频互动聊天", R.drawable.b));
		girls.add(new Girl("让女神打开你寂寞的心扉汇聚国内知名主播，不同的演唱风格，高质量的视频画面，Live现场的音效，炫丽丰富的礼物", R.drawable.c));
		girls.add(new Girl("眼前出现了一张清丽白腻的脸庞,小嘴边带着俏皮的微笑,月光照射在她明彻的眼睛之中,宛然便是两点明星", R.drawable.d));
		girls.add(new Girl("她年方十七，身材高桃，体态轻盈，言行举止端庄娴雅。乌发如漆，肌肤如玉，美目流盼，一颦一笑之间流露出一种说不出的风韵。她宛如一朵含苞待放的牡丹花，美而不妖，艳而不俗，千娇百媚，无与伦比", R.drawable.e));
		girls.add(new Girl("她约莫十七八岁年纪,一张瓜子脸,容貌甚美", R.drawable.f));
		girls.add(new Girl("心下得意,不由得笑魇如花,明艳不可方物", R.drawable.g));
		girls.add(new Girl("一个绿杉少女手执双桨,缓缓划水而来,口中唱着小曲", R.drawable.h));
		girls.add(new Girl("犹如仙女一般,不禁看得呆了.那船慢慢荡近,只见那女子方当韶龄,不过十五六岁年纪,肌肤胜雪,娇美无比,容色绝丽,不可逼视", R.drawable.j));
		girls.add(new Girl("这女童约莫十三四岁年纪,穿一身翠绿衣衫,皮肤雪白,一张脸蛋清秀可爱", R.drawable.k));
		girls.add(new Girl("只见那少女一双纤手皓肤如玉,映着绿波,便如透明一般", R.drawable.l));
		girls.add(new Girl("只见她抿着嘴,笑吟吟的斜眼瞅着自己,肤白如新剥鲜菱,嘴角边一粒细细的黑痣,更增俏媚只见她一张瓜子脸,颇为俏丽,年纪似尚比他小着一两岁", R.drawable.m));
		girls.add(new Girl("只见一个身穿藕色纱衫的女郎,脸朝着花树,身形苗条,长发披向背心,用一根银色丝带轻轻挽住.他望着她的背影,只觉这女郎身旁似有烟霞轻笼,当真非尘世中人", R.drawable.n));
		girls.add(new Girl("但见她一般的瓜子脸蛋,眼如点漆,清秀绝俗", R.drawable.o));
		//获取返回数据
		listener.onComplete(girls);
		
	}
	
}
