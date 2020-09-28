package pl.longhorn.autopoly.district;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.longhorn.autopoly.district.field.AutopolyField;
import pl.longhorn.autopoly.district.field.empty.EmptyFieldCommand;
import pl.longhorn.autopoly.district.field.start.StartFieldCommand;
import pl.longhorn.autopoly.district.field.street.SequenceStreetFieldCommand;
import pl.longhorn.autopoly.id.IdFactory;
import pl.longhorn.autopoly.randomizer.Randomizer;
import pl.longhorn.autopoly.street.name.RandomStreetNameQuery;

@Service
@RequiredArgsConstructor
public class DistrictDetailsCommand {

    private final DistrictDetailsRepository districtDetailsRepository;
    private final IdFactory idFactory;
    private final RandomStreetNameQuery randomStreetNameQuery;
    private final StartFieldCommand startFieldCommand;
    private final Randomizer randomizer;

    public DistrictDetails prepareFields() {
        DistrictDetails districtDetails = new DistrictDetails(startFieldCommand.prepare(idFactory.generate()));

        SequenceStreetFieldCommand sequenceStreetFieldCommand = new SequenceStreetFieldCommand(randomizer);
        EmptyFieldCommand emptyFieldCommand = new EmptyFieldCommand(idFactory);

        // 1
        generateDistrict2(sequenceStreetFieldCommand, emptyFieldCommand.prepare(), districtDetails);
        districtDetails.addNoDistricted(emptyFieldCommand.prepare());
        districtDetails.addNoDistricted(emptyFieldCommand.prepare());
        generateDistrict3(sequenceStreetFieldCommand, emptyFieldCommand.prepare(), districtDetails);

        districtDetails.addNoDistricted(emptyFieldCommand.prepare());

        // 2
        generateDistrict3(sequenceStreetFieldCommand, emptyFieldCommand.prepare(), districtDetails);
        districtDetails.addNoDistricted(emptyFieldCommand.prepare());
        generateDistrict3(sequenceStreetFieldCommand, emptyFieldCommand.prepare(), districtDetails);

        districtDetails.addNoDistricted(emptyFieldCommand.prepare());

        // 3
        generateDistrict3(sequenceStreetFieldCommand, emptyFieldCommand.prepare(), districtDetails);
        districtDetails.addNoDistricted(emptyFieldCommand.prepare());
        generateDistrict3(sequenceStreetFieldCommand, emptyFieldCommand.prepare(), districtDetails);

        districtDetails.addNoDistricted(emptyFieldCommand.prepare());

        // 4
        generateDistrict3(sequenceStreetFieldCommand, emptyFieldCommand.prepare(), districtDetails);
        districtDetails.addNoDistricted(emptyFieldCommand.prepare());
        districtDetails.addNoDistricted(emptyFieldCommand.prepare());
        generateDistrict2(sequenceStreetFieldCommand, emptyFieldCommand.prepare(), districtDetails);

        districtDetailsRepository.save(districtDetails);

        return districtDetails;
    }

    private void generateDistrict2(SequenceStreetFieldCommand sequenceStreetFieldCommand, AutopolyField nonDistrictedBreak, DistrictDetails result) {
        String districtId = idFactory.generate();
        result.addDistricted(sequenceStreetFieldCommand.generate(idFactory.generate(), randomStreetNameQuery.getRandom(), districtId), districtId);
        result.addNoDistricted(nonDistrictedBreak);
        result.addDistricted(sequenceStreetFieldCommand.generate(idFactory.generate(), randomStreetNameQuery.getRandom(), districtId), districtId);
    }

    private void generateDistrict3(SequenceStreetFieldCommand sequenceStreetFieldCommand, AutopolyField nonDistrictedBreak, DistrictDetails result) {
        String districtId = idFactory.generate();
        result.addDistricted(sequenceStreetFieldCommand.generate(idFactory.generate(), randomStreetNameQuery.getRandom(), districtId), districtId);
        result.addNoDistricted(nonDistrictedBreak);
        result.addDistricted(sequenceStreetFieldCommand.generate(idFactory.generate(), randomStreetNameQuery.getRandom(), districtId), districtId);
        result.addDistricted(sequenceStreetFieldCommand.generate(idFactory.generate(), randomStreetNameQuery.getRandom(), districtId), districtId);
    }
}
