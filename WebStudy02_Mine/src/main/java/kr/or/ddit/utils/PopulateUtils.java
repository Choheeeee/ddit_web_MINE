package kr.or.ddit.utils;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.AbstractConverter;

import kr.or.ddit.vo.MemberVO;
import lombok.val;


//날짜형식을 변환해주는 컨버터를 만들고, beanutils에 등록함
public class PopulateUtils {
	static {
		AbstractConverter converter = new AbstractConverter() {

			@Override
			protected <T> T convertToType(Class<T> type, Object value) throws Throwable {
				if(value==null || value.toString().isEmpty()) return null;
				else
					return (T) type.getDeclaredMethod("parse", CharSequence.class).invoke(null, value.toString());
//					return (T) LocalDateTime.parse(value.toString());
//					return (T) LocalDate.parse(value.toString());
			}

			@Override
			protected Class<?> getDefaultType() {
				return LocalDate.class;
			}
			
			
		};
		ConvertUtils.register(converter, LocalDate.class);
		ConvertUtils.register(converter, LocalDateTime.class);
	}

	public static <T> void populate(T bean, Map<String, ? extends Object> parameterMap) {
		try {
			BeanUtils.populate(bean, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}













