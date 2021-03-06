/**
 * Licensed to the TomTom International B.V. under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  TomTom International B.V.
 * licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
/**
 *  Copyright (C) 2009-2012 TomTom International B.V.
 *
 *   TomTom (Legal Department)
 *   Email: legal@tomtom.com
 *
 *   TomTom (Technical contact)
 *   Email: openlr@tomtom.com
 *
 *   Address: TomTom International B.V., Oosterdoksstraat 114, 1011DK Amsterdam,
 *   the Netherlands
 */
package openlr.datex2;

import openlr.LocationReference;
import openlr.PhysicalDecoder;
import openlr.PhysicalFormatException;
import openlr.datex2.OpenLRDatex2Exception.XMLErrorType;
import openlr.datex2.decoder.GeoCoordDecoder;
import openlr.datex2.decoder.LineDecoder;
import openlr.datex2.decoder.PoiAccessDecoder;
import openlr.datex2.decoder.PointAlongDecoder;
import openlr.rawLocRef.RawLocationReference;
import eu.datex2.schema._2_0rc2._2_0.OpenlrGeoCoordinate;
import eu.datex2.schema._2_0rc2._2_0.OpenlrPoiWithAccessPoint;
import eu.datex2.schema._2_0rc2._2_0.OpenlrPointAlongLine;
import eu.datex2.schema._2_0rc2._2_0.OpenlrPointLocationReference;

/**
 * The class OpenLRDatex2Decoder is the implementation of the PhysicalDecoder
 * interface. This Decoder gets a Datex II representation and transforms it into
 * a RawLocationReference.
 * 
 * <p>
 * OpenLR is a trade mark of TomTom International B.V.
 * <p>
 * email: software@openlr.org
 * 
 * @author TomTom International B.V.
 */
public class OpenLRDatex2Decoder implements PhysicalDecoder {	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final RawLocationReference decodeData(final LocationReference lr)
			throws PhysicalFormatException {
		Object data = lr.getLocationReferenceData();
		if (!(data instanceof Datex2Location)) {
			throw new OpenLRDatex2Exception(XMLErrorType.DATA_ERROR,
					"incorrect data class");
		}
		RawLocationReference rawLocRef = null;
		Datex2Location xmlData = (Datex2Location) data;
		if (xmlData.isLineLocation()) {
			LineDecoder decoder = new LineDecoder();
			rawLocRef = decoder.decodeData(lr.getID(), xmlData.getLineLocation());
		} else if (xmlData.isPointLocation()) {
			OpenlrPointLocationReference pointLoc = xmlData.getPointLocation();
			OpenlrGeoCoordinate gCoord = pointLoc.getOpenlrGeoCoordinate();
			OpenlrPointAlongLine pal = pointLoc.getOpenlrPointAlongLine();
			OpenlrPoiWithAccessPoint palwap = pointLoc
					.getOpenlrPoiWithAccessPoint();
			if (gCoord == null && pal == null && palwap == null) {
				throw new OpenLRDatex2Exception(XMLErrorType.DATA_ERROR,
						"no point location found");
			}
			if (gCoord != null) {
				GeoCoordDecoder decoder = new GeoCoordDecoder();
				rawLocRef = decoder.decodeData(lr.getID(), gCoord);
			} else if (pal != null) {
				PointAlongDecoder decoder = new PointAlongDecoder();
				rawLocRef = decoder.decodeData(lr.getID(), pal);
			} else if (palwap != null) {
				PoiAccessDecoder decoder = new PoiAccessDecoder();
				rawLocRef = decoder.decodeData(lr.getID(), palwap);
			}
		}
		return rawLocRef;
	}



	/**
	 * {@inheritDoc}
	 */
	public final Class<?> getDataClass() {
		return Datex2Location.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getDataFormatIdentifier() {
		return OpenLRDatex2Constants.IDENTIFIER;
	}





}
