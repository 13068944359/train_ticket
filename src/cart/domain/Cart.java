package cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();
	
	public double getTotal(){       //计算购物车所有商品价格之和
		BigDecimal total = new BigDecimal("0");
		
		for(CartItem cartItem : map.values()){
			BigDecimal subTotal = new BigDecimal(cartItem.getSubTatal() + "");
			total = total.add( subTotal);
		}
		return total.doubleValue();
	}
	
	public void add(CartItem cartitem){ //添加条目
		
		if(map.containsKey(cartitem.getTicket().getTid()+"")){  //原来的车中是否存在该条目
			
			CartItem item = map.get(cartitem.getTicket().getTid()+"");                //注意这里GET之后没有REMOVE该对象，很可能是问题所在
			item.setCount(item.getCount() + cartitem.getCount());  //旧条目，新条目数量合并
			map.put(cartitem.getTicket().getTid()+"", item);       //
		}else{
			map.put(cartitem.getTicket().getTid()+"", cartitem);
		}
	}
	
	
	public void clear(){   //清空条目
		map.clear();
	}
	
	public void delete(String tid){   //删除制定条目
		
		map.remove(tid);
	}
	
	
	public Collection<CartItem> getCartItenList(){       //获取所有条目
		if(map.isEmpty()){        //jsp页面的if判断需要判断是否为null，如果不加入这个判断，当map为（空）时会干扰相关逻辑的判断
			return null;
		}else{
			return map.values();
		}
		
	}
}
