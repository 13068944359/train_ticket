package cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();
	
	public double getTotal(){       //���㹺�ﳵ������Ʒ�۸�֮��
		BigDecimal total = new BigDecimal("0");
		
		for(CartItem cartItem : map.values()){
			BigDecimal subTotal = new BigDecimal(cartItem.getSubTatal() + "");
			total = total.add( subTotal);
		}
		return total.doubleValue();
	}
	
	public void add(CartItem cartitem){ //�����Ŀ
		
		if(map.containsKey(cartitem.getTicket().getTid()+"")){  //ԭ���ĳ����Ƿ���ڸ���Ŀ
			
			CartItem item = map.get(cartitem.getTicket().getTid()+"");                //ע������GET֮��û��REMOVE�ö��󣬺ܿ�������������
			item.setCount(item.getCount() + cartitem.getCount());  //����Ŀ������Ŀ�����ϲ�
			map.put(cartitem.getTicket().getTid()+"", item);       //
		}else{
			map.put(cartitem.getTicket().getTid()+"", cartitem);
		}
	}
	
	
	public void clear(){   //�����Ŀ
		map.clear();
	}
	
	public void delete(String tid){   //ɾ���ƶ���Ŀ
		
		map.remove(tid);
	}
	
	
	public Collection<CartItem> getCartItenList(){       //��ȡ������Ŀ
		if(map.isEmpty()){        //jspҳ���if�ж���Ҫ�ж��Ƿ�Ϊnull���������������жϣ���mapΪ���գ�ʱ���������߼����ж�
			return null;
		}else{
			return map.values();
		}
		
	}
}
