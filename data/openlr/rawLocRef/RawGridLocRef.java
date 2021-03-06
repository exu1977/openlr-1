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
package openlr.rawLocRef;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import openlr.LocationType;
import openlr.map.GeoCoordinates;

/**
 * Implementation of the RawLocationReference interface for grid locations.
 * 
 * <p>
 * OpenLR is a trade mark of TomTom International B.V.
 * <p>
 * email: software@openlr.org
 * 
 * @author DLR e.V. (RE)
 */
public class RawGridLocRef extends RawLocationReference {

	/** The leftmost point. */
	private final GeoCoordinates lowerLeft;

	/** The rightmost point. */
	private final GeoCoordinates upperRight;

	/** The number of columns. */
	private final int ncols;

	/** The number of rows. */
	private final int nrows;

	/**
	 * Instantiates a new raw encoder location reference for a grid location.
	 * 
	 * @param idValue
	 *            the id
	 * @param ll
	 *            the ll
	 * @param ur
	 *            the ur
	 * @param cols
	 *            the cols
	 * @param rows
	 *            the rows
	 */
	public RawGridLocRef(final String idValue, final GeoCoordinates ll,
			final GeoCoordinates ur, final int cols, final int rows) {
		super(idValue, LocationType.GRID);
		lowerLeft = ll;
		upperRight = ur;
		ncols = cols;
		nrows = rows;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final GeoCoordinates getLowerLeftPoint() {
		return lowerLeft;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final GeoCoordinates getUpperRightPoint() {
		return upperRight;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getNumberOfColumns() {
		return ncols;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getNumberOfRows() {
		return nrows;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id: ").append(id);
		sb.append(" locType: ").append(locType);
		if (lowerLeft != null) {
			sb.append(" lower left: ");
			sb.append(lowerLeft);
		}
		if (upperRight != null) {
			sb.append(" upper right: ");
			sb.append(upperRight);
		}
		sb.append(" number of cols: ").append(ncols);
		sb.append(" number of rows: ").append(nrows);
		return sb.toString();
	}

	/**
	 * { {@inheritDoc}
	 */
	@Override
	public final int calculateHashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(lowerLeft).append(locType).append(upperRight)
				.append(ncols).append(nrows);
		return builder.toHashCode();
	}

	/**
	 * { {@inheritDoc}
	 */
	@Override
	public final boolean equals(final Object obj) {
		if (!(obj instanceof RawGridLocRef)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		RawGridLocRef other = (RawGridLocRef) obj;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(upperRight, other.upperRight)
				.append(locType, other.locType)
				.append(lowerLeft, other.lowerLeft).append(ncols, other.ncols)
				.append(nrows, other.nrows);
		return builder.isEquals();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		return getHashCode();
	}
}
