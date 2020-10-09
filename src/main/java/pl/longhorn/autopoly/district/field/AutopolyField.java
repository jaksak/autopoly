package pl.longhorn.autopoly.district.field;

public interface AutopolyField {
    String getId();

    AutopolyFieldDetailsView toView();

    AutopolyField reset();
}
