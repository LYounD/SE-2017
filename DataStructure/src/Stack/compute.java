package Stack;

import javax.swing.JOptionPane;

public class compute {
	public static void main(String args[])
	{
		Stack operands = new Stack();
		Stack operators = new Stack();
		Stack compute = new Stack();
		Object operand = null;
		Object operator = null;
		int opers;
		int result = 0;
		String result_str = "";
		while(true) //���� �Է�
		{
			while(true) //�ǿ�����
			{
				try
				{
					operand = (Object)Integer.parseInt(JOptionPane.showInputDialog("���ڸ� �Է��ϼ���."));
					operands.push((Object)operand);
					break;
				}
				catch(Exception e)
				{
					System.out.println("<Error>���ڸ� �Է��ϼ���.");
					continue;
				}
			}
			while(true) //������
			{
				operator = JOptionPane.showInputDialog("�����ڸ� �Է��ϼ���.(+,-,*,/,=)");
				if(!operator.equals("+") && !operator.equals("-") && !operator.equals("*") && !operator.equals("/") && !operator.equals("="))
				{
					System.out.println("<Error>������(+,-,*,/,=)�� �Է��ϼ���.");
					continue;
				}
				operators.push(operator);
				break;
			}
			if(operator.equals("="))
				break;
		}
		opers = operands.size();
		for(int i=0; i<opers; i++)
		{
			compute.push(operators.pop());
			System.out.print("Operators ");
			operators.print();
			System.out.print("Operands ");
			operands.print();
			compute.push(operands.pop());
			System.out.print("Operators ");
			operators.print();
			System.out.print("Operands ");
			operands.print();
		}
		result = (int)compute.pop();
		result_str += result;
		while(true)
		{
			operator = compute.pop();
			result_str += operator;
			operand = compute.pop();
			if(operand != null) result_str += operand;
			switch((String)operator)
			{
			case "+":
				result += (int)operand;
				break;
			case "-":
				result -= (int)operand;
				break;
			case "*":
				result *= (int)operand;
				break;
			case "/":
				result /= (int)operand;
				break;
			case "=":
				System.out.println("��� : "+result_str+result);
				return;
			}
		}
	}
}
