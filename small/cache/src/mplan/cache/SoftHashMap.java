package mplan.cache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @describe 基于SoftReference的的缓存
 * @author zhujh9
 * @createdate 2020-08-26 上午9:50:17
 * 
 **/
public class SoftHashMap<K, V> implements Map<K, V> {

	private Map<K, SoftValue<K, V>> map = null;

	private ReferenceQueue<? super V> queue = new ReferenceQueue<>();

	public SoftHashMap() {
		 Object newProxyInstance = Proxy.newProxyInstance(getClass().getClassLoader(), getClass().getInterfaces(),
				(Object proxy, Method method, Object[] args) -> {
					return method.invoke(this, args);
				});
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		return false;
	}

	@Override
	public V get(Object key) {
		return null;
	}

	@Override
	public V put(K key, V value) {
		processQueue();
		SoftValue<K, V> sv = new SoftValue<>(key, value, queue);
		SoftValue<K, V> oldValue = map.put(key, sv);
		return oldValue != null ? oldValue.get() : null;
	}

	@Override
	public V remove(Object key) {

		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {

	}

	@Override
	public void clear() {

	}

	@Override
	public Set<K> keySet() {
		return null;
	}

	@Override
	public Collection<V> values() {
		return null;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 清除map中已经被GC的缓存
	 */
	@SuppressWarnings("unchecked")
	private void processQueue() {
		SoftValue<K, V> sv;
		while ((sv = (SoftValue<K, V>) queue.poll()) != null)
			map.remove(sv.k);
	}

	private static class SoftValue<K, V> extends SoftReference<V> {
		private K k;

		public SoftValue(K k, V v, ReferenceQueue<? super V> q) {
			super(v, q);
			this.k = k;
		}
	}
}
