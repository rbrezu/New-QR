/*
 * Copyright 2007 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ro.whitesoftware.qr.detector;

/**
 * <p>Encapsulates information about finder patterns in an image, including the location of
 * the three finder patterns, and their estimated module size.</p>
 *
 * @author Sean Owen
 */
public final class FinderPatternInfo {

  private final FinderPattern left;
//  private final FinderPattern top;
//  private final FinderPattern bottom;
//  private final FinderPattern right;


  public FinderPatternInfo(FinderPattern[] patternCenters) {
    this.left = patternCenters[0];
//    this.top = patternCenters[1];
//    this.right = patternCenters[2];
//    this.bottom = patternCenters[3];
  }

  public FinderPattern getLeft() {
    return left;
  }

//  public FinderPattern getTop() {
//    return top;
//  }
//
//  public FinderPattern getBottom() {
//    return bottom;
//  }
//
//  public FinderPattern getRight() {
//    return right;
//  }
}
