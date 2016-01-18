package com.xiaojiang.exception;


public class ParameterException extends ZYBException {

	private static final long serialVersionUID = 1L;
	
	public final static int PARAM_LACK = 101;
    public final static int PARAM_FORMAT_ERROR = 102;
    public final static int PARAM_TYPE_ERROR = 103;
    public final static int PARAM_LOGIC_ERROR = 104;
    public final static int PARAM_LENGTH_ERROR = 105;
    public final static int PARAM_RANGE_ERROR = 106;

	protected ParameterException(int errcode, String reason){
		super(1, errcode, reason, null, null);
	}
	
	/**
	 * 缺少参数错误.
	 * @param reason
	 * @throws ParameterException
	 */
	public static void parameterLackError(String reason) throws ParameterException{
		throw new ParameterException(PARAM_LACK, reason);
	}
	/**
	 * 参数格式错误.
	 * @param reason
	 * @throws ParameterException
	 */
	public static void parameterFormatError(String reason) throws ParameterException{
		throw new ParameterException(PARAM_FORMAT_ERROR, reason);
	}
	/**
	 * 参数类型错误.
	 * @param reason
	 * @throws ParameterException
	 */
	public static void parameterTypeError(String reason) throws ParameterException{
		throw new ParameterException(PARAM_TYPE_ERROR, reason);
	}
	/**
	 * 参数逻辑错误.
	 * @param reason
	 * @throws ParameterException
	 */
	public static void parameterConflictError(String reason) throws ParameterException{
		throw new ParameterException(PARAM_LOGIC_ERROR, reason);
	}
	
	/**
	 * 参数长度不正确 .
	 * @param reason
	 * @throws ParameterException
	 */
	public static void parameterTooLongError(String reason) throws ParameterException{
		throw new ParameterException(PARAM_LENGTH_ERROR, reason);
	}
	
	/**
	 * 参数范围不正确 .
	 * @param reason
	 * @throws ParameterException
	 */
	public static void parameterScopeError(String reason) throws ParameterException{
		throw new ParameterException(PARAM_RANGE_ERROR, reason);
	}

}
