package pl.longhorn.autopoly.district.player;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.longhorn.autopoly.district.DistrictDetails;
import pl.longhorn.autopoly.district.DistrictDetailsQuery;
import pl.longhorn.autopoly.district.field.cqrs.FieldQuery;
import pl.longhorn.autopoly.district.field.definition.start.FakeStartFieldFactory;
import pl.longhorn.autopoly.district.field.definition.street.FakeStreetFieldFactory;
import pl.longhorn.autopoly.player.ownership.cqrs.PlayerOwnershipQuery;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class PlayerDistrictCommandTest {

    @Test
    public void shouldReturnOnlyFullDistrict() {
        PlayerDistrictCommand playerDistrictCommand = new PlayerDistrictCommand(preparePlayerOwnershipQuery(), prepareFieldQuery(), prepareDistrictDetailsQuery());

        var playerDistrict = playerDistrictCommand.prepare("1");

        assertEquals(1, playerDistrict.getFull().size());
    }

    private DistrictDetailsQuery prepareDistrictDetailsQuery() {
        DistrictDetailsQuery districtDetailsQuery = Mockito.mock(DistrictDetailsQuery.class);
        Mockito.when(districtDetailsQuery.get()).thenReturn(prepareDistrictDetails());
        return districtDetailsQuery;
    }

    private DistrictDetails prepareDistrictDetails() {
        DistrictDetails districtDetails = new DistrictDetails(FakeStartFieldFactory.prepare());
        districtDetails.add(FakeStreetFieldFactory.getStreetField("1", "1"));
        districtDetails.add(FakeStreetFieldFactory.getStreetField("2", "1"));
        districtDetails.add(FakeStreetFieldFactory.getStreetField("3", "1"));
        districtDetails.add(FakeStreetFieldFactory.getStreetField("4", "2"));
        districtDetails.add(FakeStreetFieldFactory.getStreetField("5", "2"));
        districtDetails.add(FakeStreetFieldFactory.getStreetField("6", "2"));
        return districtDetails;
    }

    private FieldQuery prepareFieldQuery() {
        FieldQuery fieldQuery = Mockito.mock(FieldQuery.class);
        Mockito.when(fieldQuery.getField("1")).thenReturn(FakeStreetFieldFactory.getStreetField("1", "1"));
        Mockito.when(fieldQuery.getField("2")).thenReturn(FakeStreetFieldFactory.getStreetField("2", "1"));
        Mockito.when(fieldQuery.getField("3")).thenReturn(FakeStreetFieldFactory.getStreetField("3", "1"));
        Mockito.when(fieldQuery.getField("4")).thenReturn(FakeStreetFieldFactory.getStreetField("4", "2"));
        return fieldQuery;
    }

    private PlayerOwnershipQuery preparePlayerOwnershipQuery() {
        PlayerOwnershipQuery playerOwnershipQuery = Mockito.mock(PlayerOwnershipQuery.class);
        Mockito.when(playerOwnershipQuery.get(any())).thenReturn(List.of("1", "2", "3", "4"));
        return playerOwnershipQuery;
    }

}