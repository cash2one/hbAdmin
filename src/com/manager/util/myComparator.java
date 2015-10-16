package com.manager.util;

import java.text.CollationKey;
import java.text.Collator;
import java.util.Comparator;


	public class myComparator   implements   Comparator {

		   //����Collator��
		   private Collator collator = Collator.getInstance();//���鿴����api���

		   public myComparator() {
		   }


		   /**
		    * compare ʵ������
		    */
		    public int compare(Object o1, Object o2) {
		     //���ַ�ת��Ϊһϵ�б��أ����ǿ����Ա�����ʽ�� CollationKeys ��Ƚ�
		    CollationKey key1=collator.getCollationKey(o1.toString());//Ҫ�벻��ִ�Сд���бȽ���o1.toString().toLowerCase()
		    CollationKey key2=collator.getCollationKey(o2.toString());

		     return key1.compareTo(key2);//���صķֱ�Ϊ1,0,-1 �ֱ�����ڣ����ڣ�С�ڡ�Ҫ�밴����ĸ��������Ļ� �Ӹ�-����
		   }
		}



