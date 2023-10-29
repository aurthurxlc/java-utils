package cn.aurthur.exception;

public class CheckedFailedException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 构造一个空的异常.
     */
    public CheckedFailedException() {
        super();
    }

    /**
     * 构造一个异常, 指明异常的详细信息.
     *
     * @param message 详细信息
     */
    public CheckedFailedException(String message) {
        super(message);
    }

    /**
     * 构造一个异常, 指明引起这个异常的起因.
     *
     * @param cause 异常的起因
     */
    public CheckedFailedException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造一个异常, 指明引起这个异常的起因.
     *
     * @param message 详细信息
     * @param cause   异常的起因
     */
    public CheckedFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
