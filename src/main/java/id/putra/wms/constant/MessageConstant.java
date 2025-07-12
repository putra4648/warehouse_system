package id.putra.wms.constant;

public class MessageConstant {
  private MessageConstant() {
  }

  public static String MESSAGE = """
      <div
        class="alert %s alert-dismissible"
        role="alert"
      >
        <strong>%s </strong> %s
        <button
          type="button"
          class="btn-close"
          data-bs-dismiss="alert"
          aria-label="Close"
        >
        </button>
      </div>
                    """;
}
