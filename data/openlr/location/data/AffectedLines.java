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
package openlr.location.data;

import java.util.List;

import openlr.map.Line;

/**
 * The enumeration LocationType specifies the different types of location
 * supported by the OpenLR method.
 * 
 * <p>
 * OpenLR is a trade mark of TomTom International B.V.
 * <p>
 * email: software@openlr.org
 * 
 * @author TomTom International B.V.
 */
public interface AffectedLines {
	
	/** 
	 * Gets a list of lines that are fully covered by the area location, if the
	 * decoded area location is valid. Of note is that, in rare cases, this may
	 * include (perfectly straight) lines that lie exactly on the boundary of
	 * the area.
	 * 
	 * @return list of fully covered lines if the decoded area location is
	 *         valid, otherwise null
	 */
	List<Line> getCoveredLines();


	/** 
	 * Gets a list of lines that are intersected (but not fully covered) by the
	 * boundary of the area location, if the decoded area location is valid.
	 * 
	 * @return list of intersected lines if the decoded area location is valid,
	 *         otherwise null
	 */
	List<Line> getIntersectedLines();
	
	/**
	 * Checks for covered lines.
	 *
	 * @return true, if successful
	 */
	boolean hasCoveredLines();
	
	/**
	 * Checks for intersected lines.
	 *
	 * @return true, if successful
	 */
	boolean hasIntersectedLines();
	
	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 */
	boolean isEmpty();

}
