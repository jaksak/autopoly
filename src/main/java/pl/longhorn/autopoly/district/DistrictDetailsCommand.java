package pl.longhorn.autopoly.district;

import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.definition.empty.EmptyFieldCommand;
import pl.longhorn.autopoly.district.field.definition.start.StartFieldCommand;
import pl.longhorn.autopoly.district.field.definition.station.StationFieldCommand;
import pl.longhorn.autopoly.district.field.definition.street.SequenceStreetFieldCommand;
import pl.longhorn.autopoly.district.unique.UniqueStreetNameProvider;
import pl.longhorn.autopoly.name.street.RandomStreetNameQuery;
import pl.longhorn.autopoly.player.ownership.cqrs.FieldOwnershipQuery;
import pl.longhorn.autopoly.player.ownership.cqrs.PlayerOwnershipQuery;
import pl.longhorn.autopoly.util.id.IdFactory;
import pl.longhorn.autopoly.util.randomizer.Randomizer;

@Service
public class DistrictDetailsCommand {

    private final DistrictRepository districtRepository;
    private final IdFactory idFactory;
    private final StartFieldCommand startFieldCommand;
    private final Randomizer randomizer;
    private final UniqueStreetNameProvider uniqueStreetNameProvider;
    private final StationFieldCommand stationFieldCommand;
    private final DistrictDetailsQuery districtDetailsQuery;
    private final PlayerOwnershipQuery playerOwnershipQuery;
    private final FieldOwnershipQuery fieldOwnershipQuery;

    public DistrictDetailsCommand(DistrictRepository districtRepository, IdFactory idFactory, RandomStreetNameQuery randomStreetNameQuery, StartFieldCommand startFieldCommand, Randomizer randomizer, StationFieldCommand stationFieldCommand, DistrictDetailsQuery districtDetailsQuery, PlayerOwnershipQuery playerOwnershipQuery, FieldOwnershipQuery fieldOwnershipQuery) {
        this.districtRepository = districtRepository;
        this.idFactory = idFactory;
        this.startFieldCommand = startFieldCommand;
        this.randomizer = randomizer;
        this.uniqueStreetNameProvider = new UniqueStreetNameProvider(randomStreetNameQuery);
        this.stationFieldCommand = stationFieldCommand;
        this.districtDetailsQuery = districtDetailsQuery;
        this.playerOwnershipQuery = playerOwnershipQuery;
        this.fieldOwnershipQuery = fieldOwnershipQuery;
    }

    public DistrictDetails prepareFields() {
        DistrictDetails districtDetails = new DistrictDetails(startFieldCommand.prepare());

        SequenceStreetFieldCommand sequenceStreetFieldCommand = new SequenceStreetFieldCommand(randomizer);
        EmptyFieldCommand emptyFieldCommand = new EmptyFieldCommand(idFactory);

        // 1
        generateDistrict2(sequenceStreetFieldCommand, emptyFieldCommand.prepare(), districtDetails);
        districtDetails.addNoDistricted(emptyFieldCommand.prepare());
        districtDetails.addNoDistricted(stationFieldCommand.prepare());
        generateDistrict3After1Break(sequenceStreetFieldCommand, emptyFieldCommand.prepare(), districtDetails);

        districtDetails.addNoDistricted(emptyFieldCommand.prepare());

        // 2
        generateDistrict3After1Break(sequenceStreetFieldCommand, emptyFieldCommand.prepare(), districtDetails);
        districtDetails.addNoDistricted(stationFieldCommand.prepare());
        generateDistrict3After1Break(sequenceStreetFieldCommand, emptyFieldCommand.prepare(), districtDetails);

        districtDetails.addNoDistricted(emptyFieldCommand.prepare());

        // 3
        generateDistrict3After1Break(sequenceStreetFieldCommand, emptyFieldCommand.prepare(), districtDetails);
        districtDetails.addNoDistricted(stationFieldCommand.prepare());
        generateDistrict3After2Break(sequenceStreetFieldCommand, emptyFieldCommand.prepare(), districtDetails);

        districtDetails.addNoDistricted(emptyFieldCommand.prepare());

        // 4
        generateDistrict3After2Break(sequenceStreetFieldCommand, emptyFieldCommand.prepare(), districtDetails);
        districtDetails.addNoDistricted(stationFieldCommand.prepare());
        districtDetails.addNoDistricted(emptyFieldCommand.prepare());
        generateDistrict2(sequenceStreetFieldCommand, emptyFieldCommand.prepare(), districtDetails);

        districtRepository.save(districtDetails);

        return districtDetails;
    }

    private void generateDistrict2(SequenceStreetFieldCommand sequenceStreetFieldCommand, AutopolyField nonDistrictedBreak, DistrictDetails result) {
        String districtId = idFactory.generate();
        result.addDistricted(sequenceStreetFieldCommand.generate(idFactory.generate(), uniqueStreetNameProvider.provide(result.getStreetFields()), districtId), districtId);
        result.addNoDistricted(nonDistrictedBreak);
        result.addDistricted(sequenceStreetFieldCommand.generate(idFactory.generate(), uniqueStreetNameProvider.provide(result.getStreetFields()), districtId), districtId);
    }

    private void generateDistrict3After1Break(SequenceStreetFieldCommand sequenceStreetFieldCommand, AutopolyField nonDistrictedBreak, DistrictDetails result) {
        String districtId = idFactory.generate();
        result.addDistricted(sequenceStreetFieldCommand.generate(idFactory.generate(), uniqueStreetNameProvider.provide(result.getStreetFields()), districtId), districtId);
        result.addNoDistricted(nonDistrictedBreak);
        result.addDistricted(sequenceStreetFieldCommand.generate(idFactory.generate(), uniqueStreetNameProvider.provide(result.getStreetFields()), districtId), districtId);
        result.addDistricted(sequenceStreetFieldCommand.generate(idFactory.generate(), uniqueStreetNameProvider.provide(result.getStreetFields()), districtId), districtId);
    }

    private void generateDistrict3After2Break(SequenceStreetFieldCommand sequenceStreetFieldCommand, AutopolyField nonDistrictedBreak, DistrictDetails result) {
        String districtId = idFactory.generate();
        result.addDistricted(sequenceStreetFieldCommand.generate(idFactory.generate(), uniqueStreetNameProvider.provide(result.getStreetFields()), districtId), districtId);
        result.addDistricted(sequenceStreetFieldCommand.generate(idFactory.generate(), uniqueStreetNameProvider.provide(result.getStreetFields()), districtId), districtId);
        result.addNoDistricted(nonDistrictedBreak);
        result.addDistricted(sequenceStreetFieldCommand.generate(idFactory.generate(), uniqueStreetNameProvider.provide(result.getStreetFields()), districtId), districtId);
    }
}
