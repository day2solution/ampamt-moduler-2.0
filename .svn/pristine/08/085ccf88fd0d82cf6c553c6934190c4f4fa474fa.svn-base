package com.ampamt.moduler.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class DumpBean {

	protected static void dumpBean(StringBuffer sb, Object o, String space) {
		if (o == null) {
			sb.append("null;");
		} else if (!o.getClass().isPrimitive() && !(o instanceof Boolean) && !(o instanceof Byte)
				&& !(o instanceof Character) && !(o instanceof Integer) && !(o instanceof Long) && !(o instanceof Float)
				&& !(o instanceof Double) && !(o instanceof Date) && !(o instanceof String) && !(o instanceof Class)) {
			sb.append(o.getClass().getName()).append("{\n");
			if (o instanceof Collection) {
				o = ((Collection) o).toArray();
			}

			if (o instanceof Map) {
				Map ht = (Map) o;
				Iterator keys = ht.keySet().iterator();

				while (keys.hasNext()) {
					Object key = keys.next();
					sb.append(space + "  " + key + "=");
					dumpBean(sb, ht.get(key), space + "  ");
					sb.append("\n");
				}
			} else if (o.getClass().isArray()) {
				for (int i = 0; i < Array.getLength(o); ++i) {
					sb.append(space + "  ");
					dumpBean(sb, Array.get(o, i), space + "  ");
					sb.append("\n");
				}
			} else {
				try {
					BeanInfo bi = Introspector.getBeanInfo(o.getClass());
					PropertyDescriptor[] pds = bi.getPropertyDescriptors();

					for (int i = 0; i < pds.length; ++i) {
						if (!pds[i].getName().equals("class")) {
							Method method = pds[i].getReadMethod();
							if (method != null) {
								Object o1 = method.invoke(o);
								if (o1 != o) {
									sb.append(space + "  " + pds[i].getName() + "=");
									dumpBean(sb, o1, space + "  ");
									sb.append("\n");
								}
							}
						}
					}
				} catch (Exception var8) {
					var8.printStackTrace();
				}
			}

			sb.append(space + "}");
		} else {
			sb.append(o.toString() + ";");
		}
	}
	
	public static String dumpBean(Object o) {
		StringBuffer sb = new StringBuffer();
		dumpBean(sb, o, "");
		return sb.toString();
	}
}
